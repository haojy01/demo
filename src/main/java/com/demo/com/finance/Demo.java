package com.bj58.com.finance;

public class Demo {

	private static String key = "IDon'tKnow";

	public static void main(String[] args) {
		String AES_ENCRYPT_STR = "BUfKQ5S57bsY/eDe5DsYA/ErH65FcD96Jpm9623DDWc="; // 加密之后的手机号号或身份证号
		String AES_DECRYPT_STR = AESUtil.base64DecodeAndAesDecrypt(AES_ENCRYPT_STR, key);
		System.out.println(AES_DECRYPT_STR);
	}
}
