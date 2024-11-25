package com.rag.rag4j.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class JwtSecretKeyGenerator {

    private static final String SEED = "RAG4J PROJECT JWT SECRET KEY BASE SEED";
    private static final String SECURE_RAND_ALGORITHM = "SHA1PRNG";
    private static final int KEY_SIZE = 256;
    private static final String KEY_ALGORITHM = "HmacSHA256";

    public static void main(String[] args) {
        String key = generateSecretKey(SEED);
        System.out.println("----- JWT KEY GENERATE SUCCESS ------");
        System.out.println("************** JWT KEY **************");
        System.out.println(key);
        System.out.println("*************************************");
    }

    private static String generateSecretKey(String seed) {
        String key = "";
        try {
            byte[] seedBytes = seed.getBytes(StandardCharsets.UTF_8);

            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RAND_ALGORITHM);
            secureRandom.setSeed(seedBytes);

            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGen.init(KEY_SIZE, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            byte[] encodedKey = secretKey.getEncoded();

            Encoder encoder = Base64.getEncoder();
            key = encoder.encodeToString(encodedKey);
        } catch (Exception e) {
            System.out.print("key generate error" + e.getMessage());
        }
        return key;
    }

}