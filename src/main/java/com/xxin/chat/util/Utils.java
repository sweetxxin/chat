package com.xxin.chat.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {

    public static String judgeBlank(String original, String newer) {
        return "".equals(newer) ? original : newer;
    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd/hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
