package com.example.service.auth;

import com.example.common.bean.AuthUser;
import com.example.common.bean.RespCodeEnum;
import com.example.common.entity.member.Member;
import com.example.common.entity.member.MemberVO;
import com.example.common.enumerate.AuthType;
import com.example.common.enumerate.RedisConstKey;
import com.example.common.exception.CustomException;
import com.example.common.feign.WechatFeignClient;
import com.example.common.pojo.wechat.WechatAuth;
import com.example.common.pojo.wechat.WechatCode2Session;
import com.example.common.utils.TokenUtils;
import com.example.service.MemberService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * description:  <br>
 * date: 2021/07/01 19:30 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Service
@Slf4j
public class AuthService {

    private final String secret = "903c0054ccf6b60b9fe906b550e557d2";

    @Autowired
    private WechatFeignClient wechatFeignClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MemberService memberService;


    public WechatAuth wechatOauth(String code) {
        String responseStr = wechatFeignClient.code2Session(secret, code);
        ObjectMapper objectMapper = new ObjectMapper();
        WechatCode2Session wechatCode2Session = null;
        try {
            wechatCode2Session = objectMapper.readValue(responseStr, WechatCode2Session.class);
            log.info("code2Session->{}", wechatCode2Session);
            if (wechatCode2Session.getErrcode() != 0) {
                throw new CustomException("登陆异常请重试！");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new CustomException("登陆异常请重试！");
        }

        Member member = memberService.getMemberByOpenid(wechatCode2Session.getOpenid());
        if (member == null) {
            stringRedisTemplate.opsForValue().set(RedisConstKey.WECHAT_TEMP_AUTH + wechatCode2Session.getOpenid(), wechatCode2Session.getSessionKey(), 10, TimeUnit.MINUTES);
            return new WechatAuth(wechatCode2Session.getOpenid(), 2);
        } else {
            String memberToken = TokenUtils.createMemberToken(new AuthUser(member.getId(), "", AuthType.WECHAT));
            return new WechatAuth(memberToken, 0);
        }
    }

    public String bindWechatInfo(WechatAuth wechatAuth) {
        try {
            String sessionKey = stringRedisTemplate.opsForValue().get(RedisConstKey.WECHAT_TEMP_AUTH.getKey() + wechatAuth.getToken());
            if (StringUtils.isEmpty(sessionKey)) {
                throw new CustomException(RespCodeEnum.MEMBER_UNBIND);
            }
            // 解析微信加密字符串
            log.info("开始解密 sessionKey->{}, text->{}, iv->{}", sessionKey, wechatAuth.getEncryptedData(), wechatAuth.getIv());
            WechatUserInfo userInfo = this.getUserInfo(wechatAuth.getEncryptedData(), sessionKey, wechatAuth.getIv());
            Member member = memberService.getMemberByOpenid(wechatAuth.getToken());
            if (member == null) {
                member = new Member();
                member.setNickName(userInfo.getNickName());
                member.setAccountStatus(1);
                member.setAvatar(userInfo.getAvatarUrl());
                member.setOpenid(wechatAuth.getToken());
                member.setCreatedDate(LocalDateTime.now());
                member.setGender(userInfo.getGender() == 1 ? "男" : "女");
                member.setLastLoginTime(LocalDateTime.now());
                if (!memberService.create(member)) {
                    throw new CustomException("用户注册失败！");
                }
                return TokenUtils.createMemberToken(new AuthUser(member.getId(), "", AuthType.WECHAT));
            }
        } catch (Exception e) {
            log.info("解析临时openid错误！{}", e.getMessage());
            throw new CustomException(RespCodeEnum.MEMBER_UNBIND);
        }
        return null;
    }


    /**
     * 获取信息
     *
     * @return
     */
    public WechatUserInfo getUserInfo(String encryptedData, String sessionkey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, StandardCharsets.UTF_8);
                log.info("res->{}", result);
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(result, WechatUserInfo.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("微信encryptedData解密失败->{}", e.getMessage());
            throw new CustomException("用户信息解密失败！");
        }
        return null;
    }

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        WechatUserInfo userInfo = authService.getUserInfo("moSDNiGuORDQr37GHP/knUzZNRdGUbgk7DsZ1NJVMtJe12tiKj8VKTjzddx9OKfRfJe/eKMOSEGI1ofqvoa2cRswYdfFyMeo9vRfFNvDecUo2519pE1lwILKAhz8WwcydGzHc+SZ63xOPdNUv3JeOV/TfEdUDqF+SrDHvZ3PsCzmx5l7GeBdf7IMeDaxzup5yIRpu2f1hpOMXgGDWpVXsH9xXAovG6ne/1yXWEBU+IT5sApL1QRls+wsTlaJx30H5e11xA4NxvM7rycFWW9IvztgSq3hGFM0/fSRBITePSA5gjgVvFdI6AMvgyoEYYdJzoFP1g6Vnn7dBNUOSoQ/3+44NwRiTz8LW3g2UHni3xrNSaj9QzWDKnaJG0uHA76r7jYJ1HqcsiO1T86RSTZGGuzZubFWTlNzRdID49aLv2U=",
                "PKzJt9LhLYy/BLZf9Ayteg==",
                "K1brogTzFP6HV8V3s6ms5Q==");
        System.out.println(userInfo);
    }

    public MemberVO getCurrentMemberInfo() {
        Integer currentUserId = TokenUtils.getCurrentPrincipalId();
        return memberService.getById(currentUserId);
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class WechatUserInfo {
        private String openId;
        private String nickName;
        private int gender;
        private String city;
        private String province;
        private String country;
        private String avatarUrl;
        private String unionId;
    }
}
