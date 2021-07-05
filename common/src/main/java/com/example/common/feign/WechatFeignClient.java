package com.example.common.feign;


import com.example.common.bean.ResponseData;
import com.example.common.pojo.wechat.AccessToken;
import com.example.common.pojo.wechat.PaidUnionId;
import com.example.common.pojo.wechat.WechatCode2Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "wechat", url = "https://api.weixin.qq.com")
public interface WechatFeignClient {

    /**
     * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     * 更多使用方法详见 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     * @param secret
     * @param code
     * @return
     */
    @GetMapping(value = "/sns/jscode2session?appid=wxa3f1edf7ef0ff8a1&secret={secret}&js_code={code}&grant_type=authorization_code")
    String code2Session(@PathVariable("secret") String secret, @PathVariable("code") String code);


    @GetMapping("/sns/jscode2session?appid=APPID&secret={secret}&js_code={code}&grant_type=authorization_code")
    ResponseData<PaidUnionId> getUserInfo(@PathVariable("secret") String secret, @PathVariable("code") String code);

    /**
     * access_token 是小程序全局唯一后台接口调用凭据
     * 调用绝大多数后台接口时都需使用。开发者可以通过 getAccessToken 接口获取并进行妥善保存。
     *
     * 为了 access_token 的安全性，后端 API 不能直接在小程序内通过 wx.request 调用
     * 即 api.weixin.qq.com 不能被配置为服务器域名。
     * 开发者应在后端服务器使用getAccessToken获取 access_token，并调用相关 API；
     * @param appid
     * @param secret
     * @return
     */
    @GetMapping("/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}")
    ResponseData<AccessToken> getAccessToken(@PathVariable("appid") String appid, @PathVariable("secret") String secret);
}
