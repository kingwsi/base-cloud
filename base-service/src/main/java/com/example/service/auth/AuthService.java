package com.example.service.auth;

import com.example.common.bean.AuthUser;
import com.example.common.bean.RespCodeEnum;
import com.example.common.entity.member.Member;
import com.example.common.enumerate.AuthType;
import com.example.common.enumerate.RedisConstKey;
import com.example.common.exception.CustomException;
import com.example.common.feign.WechatFeignClient;
import com.example.common.pojo.wechat.WechatAuthResult;
import com.example.common.pojo.wechat.WechatCode2Session;
import com.example.common.utils.TokenUtils;
import com.example.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

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


    public WechatAuthResult wechatOauth(String code) {
        String responseStr = wechatFeignClient.code2Session(secret, code);
        ObjectMapper objectMapper = new ObjectMapper();
        WechatCode2Session wechatCode2Session = null;
        try {
            wechatCode2Session = objectMapper.readValue(responseStr, WechatCode2Session.class);
            if (wechatCode2Session.getErrcode() != 0) {
                throw new CustomException("登陆异常请重试！");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new CustomException("登陆异常请重试！");
        }

        Member member = memberService.getMemberByOpenid(wechatCode2Session.getOpenid());
        if (member == null) {
//            stringRedisTemplate.opsForValue().set(RedisConstKey.WECHAT_TEMP_AUTH.getKey());
            return new WechatAuthResult("00", 0);
        } else {
            AuthUser authUser = new AuthUser(member.getId(), "", AuthType.WECHAT);
            String memberToken = TokenUtils.createMemberToken(authUser);
            return new WechatAuthResult(memberToken, 0);
        }
    }

    public static void main(String[] args) {
        String result = decryptData(
                "+6fr9M+bNeRk8LQOOuxqBLuzsydnIh7D2UvImuWicfzJsbrRFptPr7yXHubgTdRd6JHwvjDXD+Q9L0oeTjXlBZilfipjRZJSV7nOpaq++DB5wQr6hKAPsvLmUjOpTPocpVrRDbkXRQKAgl6uTXkR8SdUL3j0zihANr3ANaz2kgg8X+iCJKSxmCuxwPswFCrYaXih2Z7+s/EqWU0ACFgaoZMNkliYBy9mF/pwzCfzsDVx+eJu1eG2UmU6e0e8rUOcEGv4KxfYntvLBN7LJEfOEfYcyemNYtVt3ALDE2sOxI4pb8XxUUO3zn+Yt7e5xWqmfqFj1YK3CCl0ILHle5JlZxGz178PsztDoCnOMoA4NwEoGfqCsUH1pM7AsyfcOh27yqqk=",
                "2gCRTCyEpjQTW5Fc89yg==",
                "XBzf8VMLBIsFBZvjOWNg=="
        );
        System.out.println("result = " + result);
    }

    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }
}
