package com.ivan.blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int j = 0; j < bytes.length; j++) {
                i = bytes[j];
                if (i < 0){
                    i+=256;
                }
                if (i < 16){
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("111111"));
    }
}
