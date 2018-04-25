package com.module.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zjj
 */
public class MyAesEncrypt {

    public static final String VIPARA = "1234567890123456";
    public static final String bm = "GBK";

    public static String encrypt(String dataPassword, String cleartext)
            throws Exception {
        SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));

        return Base64.encode(encryptedData);
    }

    public static String decrypt(String dataPassword, String encrypted)
            throws Exception {
        byte[] byteMi = Base64.decode(encrypted);
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte[] decryptedData = cipher.doFinal(byteMi);

        return new String(decryptedData,bm);
    }
}
