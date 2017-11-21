package com.bj58.com.finance;

import java.io.IOException;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Util {
    private static Logger logger = LoggerFactory.getLogger(Base64Util.class);

    /**
     * Base64 encode
     */
    public static String encode(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }

        return encode(data.getBytes(Charsets.UTF_8));
    }

    public static String encode(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * Base64 decode
     */
    public static String decodeToString(String encode) {
        if (StringUtils.isEmpty(encode)) {
            return null;
        }

        return new String(decode(encode), Charsets.UTF_8);
    }

    public static byte[] decode(String encode) {
        if (StringUtils.isEmpty(encode)) {
            return null;
        }

        try {
            return new BASE64Decoder().decodeBuffer(encode);
        } catch (IOException e) {
            logger.error("decode error, encode: {}", encode, e);
            return null;
        }
    }

} 