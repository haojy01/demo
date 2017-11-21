package com.demo.com.finance;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5 消息摘要工具类
 *
 * @author fengquanwei
 * @create 2017/8/7 11:58
 **/
public class MD5Util {
    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    public static byte[] md5(String message) {
        return md5(message, StandardCharsets.UTF_8);
    }

    public static byte[] md5(String message, Charset charset) {
        return md5(message.getBytes(charset));
    }

    public static byte[] md5(byte[] bytes) {
        MessageDigest md5MessageDigest = null;
        try {
            md5MessageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("获取 MD5 摘要异常", e);
        }
        md5MessageDigest.update(bytes);
        return md5MessageDigest.digest();
    }

    public static void main(String[] args) {
        String message = "你好";
        byte[] md5Bytes = md5(message);
        System.out.println(Arrays.toString(md5Bytes));
    }
}
