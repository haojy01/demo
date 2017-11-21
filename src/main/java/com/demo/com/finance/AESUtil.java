package com.demo.com.finance;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("restriction")
public class AESUtil {
    private static Logger logger = LoggerFactory.getLogger(AESUtil.class);

    /**
     * AES 加密
     */
    private static byte[] aesEncrypt(byte[] bytes, byte[] key) {
        if (bytes == null || bytes.length == 0 || key == null || key.length == 0) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            return cipher.doFinal(bytes);
        } catch (Exception e) {
            logger.error("aesEncrypt error, bytes: {}, key: {}", bytes, key, e);
            return null;
        }
    }

    /**
     * AES 加密并 Base64 编码
     */
    public static String aesEncryptAndBase64Encode(String content, String key) {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(key)) {
            return null;
        }

        return Base64Util.encode(aesEncrypt(content.getBytes(Charsets.UTF_8), MD5Util.md5(key)));
    }

    /**
     * AES 解密
     */
    private static byte[] aesDecrypt(byte[] encrypt, byte[] key) {
        if (encrypt == null || encrypt.length == 0 || key == null || key.length == 0) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            return cipher.doFinal(encrypt);
        } catch (Exception e) {
            logger.error("aesDecrypt error, encrypt: {}, key: {}", encrypt, key, e);
            return null;
        }
    }

    /**
     * Base64 解码并 AES 解密
     */
    public static String base64DecodeAndAesDecrypt(String base64EncodeEncrypt, String key) {
        if (StringUtils.isEmpty(base64EncodeEncrypt) || StringUtils.isEmpty(key)) {
            return null;
        }

        return new String(aesDecrypt(Base64Util.decode(base64EncodeEncrypt), MD5Util.md5(key)), Charsets.UTF_8);
    }

    // ======================================== 测试 ========================================
    /*public static void main(String[] args) throws UnsupportedEncodingException {
        String key = "sgfdsgdsgs";
        String base64EncodeEncrypt = aesEncryptAndBase64Encode("abc", key);
        System.out.println(base64EncodeEncrypt);

        String decrypt = base64DecodeAndAesDecrypt(base64EncodeEncrypt, key);
        System.out.println(decrypt);
    }*/

    //================加密身份证号=========
    public static void main(String[] args) {
        System.out.println(AESUtil.aesEncryptAndBase64Encode("510725198601047712", "IDon'tKnow"));
    }
}
