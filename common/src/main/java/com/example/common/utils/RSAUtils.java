package com.example.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Java RSA 加密工具类
 */
public class RSAUtils {
    /**
     * 密钥长度 于原文长度对应 以及越长速度越慢
     */
    private final static int KEY_SIZE = 1024;

    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPQTEiLg0dECN7tzdi8ExM4ZsNQtxdOn+sHFu5wA9Uj1tN+xcZMkXjmN+n7TI0AQNldpelqczHyTyy7LAliP62E5Wue0/CYyyC5f4+kBOu+W2yk7PZt34O1UbSlNi9Nom2ph5t4RsSXufzdwjfJ06vZbsfjbaeT8i5S4SUD9NwLwIDAQAB";
    private final static String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI9BMSIuDR0QI3u3N2LwTEzhmw1C3F06f6wcW7nAD1SPW037FxkyReOY36ftMjQBA2V2l6WpzMfJPLLssCWI/rYTla57T8JjLILl/j6QE675bbKTs9m3fg7VRtKU2L02ibamHm3hGxJe5/N3CN8nTq9lux+Ntp5PyLlLhJQP03AvAgMBAAECgYBHQ/xIb7ZwXeX5FuWD391lZdq31yo/aum22oi7OJOn0oVuntoX282u8w9YwAR4oIRrBntcm8NXJIKA1ISwt51Wgo071otVxRMCZ6/qO97nQYExrL+C899yLrz1GfVojE/ChTIpPNXWSlMKMsMRSj9gedJh9HeqPU9fK2olNu15eQJBAMGHesgYrD7uDlvTzoGr+2yP7sD8ZQicZI/p8Alh09xyOy/REvyDHp34sqkuWdBG7lQRzxtdgjhepwY28T0D9xUCQQC9fzaZZXVKE6psOzXVyG/+9/M37Y0DeeprsAIEM4d6fKAtuG4NTEXykJG0iVqbUHFSdyI1CtfJak2cOQ0HkhszAkAeCOOiSEn7HkD2OWH0N/e2OoRISSN4+aSVvS22lwslTEkpO55huAu5Yo5/uSTRMSDtxTHKQnHiQ7ZSPZak7IedAkAHA8CHqSPQXC4A16hEg6jxrUgJl/eSW178IYXRlf674AUl7B/Wyi4ErqlWH4eFHFM1lEdl40Ldp9u7nrkGwLfVAkBwzKkIZEWDnZvWAdXZb7PsF8KGyEcccT1p4O4FVyATw3PlI18D5xkK/ROhn0h6FEQgtB60Rkrxp4tBtR/MPzCM";

    /**
     * 随机生成密钥对
     */
    public static Map<Integer, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        Map<Integer, String> keyMap = new HashMap<>();
        //0表示公钥
        keyMap.put(0, publicKeyString);
        //1表示私钥
        keyMap.put(1, privateKeyString);
        return keyMap;
    }

    /**
     * RSA公钥加密
     *
     * @param text      加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String text, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
    }

    public static String encrypt(String text) throws Exception {
        return encrypt(text, PUBLIC_KEY);
    }

    /**
     * RSA私钥解密
     *
     * @param raw        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String raw, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(raw);
        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    public static String decrypt(String raw) throws Exception {
        return decrypt(raw, PRIVATE_KEY);
    }

    public static void main(String[] args) throws Exception {
        long temp = System.currentTimeMillis();
        Map<Integer, String> keyMap = genKeyPair();
        //加密字符串
        System.out.println("公钥:" + keyMap.get(0));
        System.out.println("私钥:" + keyMap.get(1));
        System.out.println("生成密钥消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        String message = "HELLO ";
        System.out.println("原文:" + message);
        temp = System.currentTimeMillis();
        //通过原文，和公钥加密。
        String messageEn = encrypt(message, keyMap.get(0));
        System.out.println("密文:" + messageEn);
        System.out.println("加密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        temp = System.currentTimeMillis();
        //通过密文，和私钥解密。
        String messageDe = decrypt(messageEn, keyMap.get(1));
        System.out.println("解密:" + messageDe);
        System.out.println("解密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
    }
}
