package org.sumire.mail.utils;

import java.util.Random;

public class CodeUtil {
    /**
     * 生成6位随机验证码
     * @return 6位随机验证码
     */
    public static String generateCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
