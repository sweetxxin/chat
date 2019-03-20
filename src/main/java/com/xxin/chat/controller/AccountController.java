package com.xxin.chat.controller;

import com.alibaba.fastjson.JSON;
import com.xxin.chat.entity.Account;
import com.xxin.chat.entity.WeChat;
import com.xxin.chat.repository.AccountRepository;
import com.xxin.chat.util.ResponseResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AccountController {
    @Value("${appId}")
    private  String appId;
    @Value("${appSecret}")
    private  String appSecret ;
    @Value("${prefix}")
    private String prefix;
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping(value = "/login")
    public ResponseResult login(
            @RequestParam("code") String code
    ) throws IOException {
        ResponseResult result = new ResponseResult();
        Account account = new Account();
        WeChat weChat = getAuth(code);
        Optional<Account> accountOptional = accountRepository.findById(weChat.getOpenId());
        if (accountOptional.isPresent()){//已打开小程序
         if (accountOptional.get().getName()==null){//未注册
             result.setCode(-1);
             result.setMsg("未注册");
             result.setData(accountOptional.get().getToken());
         }else {

         }
        }else {
            account.setOpenId(weChat.getOpenId());
            account.setSessionKey(weChat.getSessionKey());
            String uuid = UUID.randomUUID().toString();
            account.setToken(uuid);
            accountRepository.save(account);
            result.setCode(-1);
            result.setMsg("未注册");
            result.setData(uuid);
        }
        return result;
    }
    @PostMapping("/register")
    public ResponseResult register(@RequestParam("account") Account account){
        ResponseResult result = new ResponseResult();
        return result;
    }
    private WeChat getAuth(String code) throws IOException {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String result = response.body().string();
        WeChat weChat = JSON.parseObject(result, WeChat.class);
        return weChat;
    }

}
