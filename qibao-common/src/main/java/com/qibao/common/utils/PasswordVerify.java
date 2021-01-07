package com.qibao.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验密码工具类
 *
 * @author qibao
 * @version v0.1 2021/1/7
 */
public class PasswordVerify {
    private static String specialChar = "[ _`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    /**
     * 特殊字符
     */
    private static final Pattern PS = Pattern.compile(specialChar);
    /**
     * 数字
     */
    private static final Pattern PN = Pattern.compile(".*\\d+.*");
    /**
     * 大写字母
     */
    private static final Pattern PU = Pattern.compile("[A-Z]]");
    /**
     * 小写字母
     */
    private static final Pattern PL = Pattern.compile("[a-z]]");


    /**
     * 校验密码长度
     *
     * @param password
     * @param length
     * @return
     */
    public static boolean lengthVerify(String password, Integer length) {
        return password.length() >= length;
    }

    /**
     * 判断密码是否包含字母大小写、数字、特殊字符中的三项
     *
     * @param password
     * @return
     */
    public static boolean containTypesVerify(String password) {
        int contains = 0;

        //判断是否有特殊字符
        Matcher matcher = PS.matcher(password);
        if (matcher.find()) {
            ++contains;
        }
        //判断是否有字母大写
        matcher = PU.matcher(password);
        if (matcher.find()) {
            ++contains;
        }
        //判断是否有字母小写
        matcher = PL.matcher(password);
        if (matcher.find()) {
            ++contains;
        }
        //判断是否有数字
        matcher = PN.matcher(password);
        if (matcher.find()) {
            ++contains;
        }

        //匹配2个以上，返回true
        return contains > 2;
    }

}
