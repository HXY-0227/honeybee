/**
 * Password Hashing With PBKDF2 (http://crackstation.net/hashing-security.htm).
 * Copyright (c) 2013, Taylor Hornby
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.honeybee.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 密码哈希
 */
public class PasswordHash {

    //加密算法
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // 盐值长度
    private static final int SALT_BYTE_SIZE = 24;

    // 哈希长度
    private static final int HASH_BYTE_SIZE = 192;

    //迭代次数，最少1000次
    private static final int PBKDF2_ITERATIONS = 1000;

    // 盐值索引
    private static final int SALT_INDEX = 0;

    // 哈希值索引
    private static final int PBKDF2_INDEX = 1;

    /**
     * 校验密码
     * @param password 输入待校验的密码
     * @param saltAndHash 加密信息
     * @return  校验结果
     */
    public static boolean validatePassword(String password, String saltAndHash) throws InvalidKeySpecException, NoSuchAlgorithmException {
        // 获取加密用的盐值和加密后正确的hash
        String[] params = saltAndHash.split(":");
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] correctHash = fromHex(params[PBKDF2_INDEX]);

        // 根据给定的盐值以及固定的迭代次数计算hash值
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

        // 比较两个hash值是否相等
        return slowEquals(correctHash, hash);
    }

    /**
     * 返回一个加盐密码后经过PBKDF2加密得到的hash值
     * @param password  用户输入的密码
     * @return  加盐密码后经过PBKDF2加密得到的hash值
     */
    public static String createHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //使用安全随机数生成盐值
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // 通过pbkdf2对密码加密
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        return toHex(salt) + ":" + toHex(hash);
    }

    /**
     * 使用pbkdf2对密码进行加密
     * @param password 密码
     * @param salt 盐值
     * @param iterations 迭代次数
     * @param bytes 生成的hash长度
     * @return 加密后得到的hash值
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterations, bytes);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return factory.generateSecret(pbeKeySpec).getEncoded();
    }

    /**
     * 使用恒定的时间比较两个字节数组，防止通过在线系统的定时攻击提取hash值，然后离线攻击
     * @param a 第一个字节数组
     * @param b 第二个字节数组
     * @return 比较结果
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        // == 经常被解释为带有分支的语句，导致代码运行时间不固定 ^保证当且仅当两个数各位都相等是才是0，否则不为0
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }
    /**
     * 将字节数组转化为十六进制字符串
     * @param array 待转化的字节数组
     * @return 转化后的字符串
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        if (paddingLength < 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        return hex;
    }

    /**
     * 将十六进制的字符串转化为字节数组
     * @param hex 待转化的字符串
     * @return 转化后得到的字节数组
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }
}
