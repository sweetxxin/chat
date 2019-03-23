package com.xxin.chat.controller;

import com.xxin.chat.entity.Account;
import com.xxin.chat.repository.AccountRepository;
import com.xxin.chat.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class BasicInfoController {

    private static String avatarLocalPath;

    static {
        try {
            avatarLocalPath = ResourceUtils.getURL("classpath:").getPath() + "/static/avatar/";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Value("${prefix}")
    private String prefix;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping(value = "/basic-with-avatar")
    public String basicWithAvatar(
            @RequestParam(value = "avatar") MultipartFile avatar,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "gender") String gender,
            @RequestParam(value = "sid", defaultValue = "无") String sid,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "klass") String klass,
            @RequestParam(value = "token") String token
    ) {

        Account account = accountRepository.findByToken(token);
        String filename = "";
        String avatarUuid = "";

        if (account == null) {
            System.out.println("错误，无此token：" + token);
            return "{\"state\":\"-1\"}";
        }
        if (!avatar.isEmpty()) {
            try {
                avatarUuid = Utils.getUuid();
                File file = new File(avatarLocalPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                filename = avatarLocalPath + avatarUuid + ".jpg";
                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(new File(filename)));
                outputStream.write(avatar.getBytes());
                outputStream.flush();
                outputStream.close();

                String old = avatarLocalPath + account.getAvatar();
                account.setAvatar(avatarUuid + ".jpg");
                File oldFile = new File(old);
                if (oldFile.exists()) {
                    if (oldFile.isFile()) {
                        oldFile.delete();
                    }
                }
                account.setUpdateAt(Utils.getCurrentTime());
                accountRepository.save(account);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("上传失败：" + e.getMessage());
                return "{\"state\":\"2\"}";
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败：" + e.getMessage());
                return "{\"state\":\"2\"}";
            }
        } else {
            System.out.println("上传失败：文件为空");
            return "{\"state\":\"2\"}";
        }


        return "{\"state\":\"0\",\"avatar\":\"" + prefix + avatarUuid + ".jpg\"}";
    }

    @PostMapping(value = "/basic-without-avatar")
    public String basicWithoutAvatar(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "gender") String gender,
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "klass") String klass,
            @RequestParam(value = "token") String token
    ) {
        Account account = accountRepository.findByToken(token);

        if (account == null) {
            System.out.println("错误，无此token：" + token);
            return "{\"state\":\"-1\"}";
        }

        return "{\"state\":\"0\"}";
    }
}
