package com.jhtsoft.util;

import java.util.UUID;

/**
 * @ClassName: GenerateUidUtil
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/10
 **/
public class GenerateUidUtil {
    public GenerateUidUtil() {
    }

    public static String generateUid() {
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        return uid.replaceAll("-", "");
    }
}