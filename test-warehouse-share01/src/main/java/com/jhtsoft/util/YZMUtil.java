package com.jhtsoft.util;

import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName: YZMUtil 验证码调用第三方接口：ruokuai
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/12
 **/
public class YZMUtil {

    public static String createByPost(String username, String password, String typeid,
                               String timeout, String softid, String softkey,String filepath){
        String result = "";
        String param = String.format("username=%s&password=%s&typeid=%s&timeout=%s&softid=%s&softkey=%s",
                username,password,typeid,"90",softid,softkey);
        try {
            File f = new File(filepath);
            if (null != f){
                int size = (int) f.length();
                byte[] data = new byte[size];
                FileInputStream fis = new FileInputStream(f);
                fis.read(data,0,size);
                if (null != fis){
                    fis.close();
                }
                if (data.length>0){
                    result = RuoKuai.httpPostImage("http://api.ruokuai.com/create.txt", param, data);
                }
            }

        }catch (Exception e){
            result = "未知问题";
        }

        return result;
    }
}
