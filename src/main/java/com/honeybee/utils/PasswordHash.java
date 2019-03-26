package com.honeybee.utils;

import java.security.SecureRandom;

/**
 * 密码哈希
 */
public class PasswordHash {

    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 24;
    private static final int PBKDF2_ITERATIONS = 1000;
    /**
     * 返回一个加盐密码经过PBKDF2算法得到的值
     * @param password  用户输入的密码
     * @return  加盐密码经过PBKDF2算法得到的值
     */
    public static String createHash(String password) {
        //使用安全随机数生成盐值
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        return "";
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {

        return null;
    }
}
