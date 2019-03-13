package com.honeybee.utils;

import java.security.SecureRandom;

/**
 * 工具类
 * @author HXY
 */
public class Utils {

    /**
     * 生成固定长度的token：包含大小写字母和数字
     * @param length 需要的token的长度
     * @return
     */
    public static String createToken(int length) {

        //token
        StringBuffer token = new StringBuffer();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int num = random.nextInt(3);
            switch (num) {
                case 0:
                    //生成0-9之间的数字
                    token.append((int)random.nextFloat() * 9);
                    break;
                case 1:
                    //生成A-Z之间的字符
                    token.append((char) (65 + (int) (random.nextFloat() * 26)));
                    break;
                case 2:
                    //生成a-z之间的字符
                    token.append((char) (97 + (int) (random.nextFloat() * 26)));
                    break;
                default:
                    break;

            }
        }
        return token.toString();
    }
}
