/**
 * BBD Service Inc
 * All Rights Reserved @2018
 */
package com.qibao.common.utils;

import org.springframework.util.Assert;

import java.util.regex.Pattern;

/**
 * 格式校验Utils
 */
public class ExamineUtils {

    /**
     * 邮箱
     */
    private final static Pattern EMAIL_REGEX_PATTERN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    /**
     * 手机
     * 简单校验大陆手机号，1开头后面跟10位数字
     */
    private final static Pattern MOBILE_REGEX_PATTERN = Pattern.compile("^1\\d{10}$");

    /**
     * 用户名
     * 4-20位字母数字_@.组成，且必须以字母或_开头
     */
    private final static Pattern USERNAME_REGEX_PATTERN = Pattern.compile("^[A-Za-z_][\\w@.]{3,19}$");

    /**
     * 密码
     */
    private final static Pattern PASSWORD_REGEX_PATTERN = Pattern.compile("^[\\w!@#$%^&*,.?:;]{6,20}$");


    /**
     * 校验用户名
     * 用户名由大小写字母数字和_组成
     *
     * @param username 用户名
     */
    public static void checkUsername(String username) {
        Assert.notNull(username, "用户名不能为空");
        Assert.isTrue(USERNAME_REGEX_PATTERN.matcher(username).matches(), "用户名为4-20位字母数字_@.组成，且必须以字母或_开头");
    }

    /**
     * 校验邮箱格式
     *
     * @param email 邮箱
     */
    public static void checkEmail(String email) {
        Assert.notNull(email, "邮箱不能为空");
        Assert.isTrue(EMAIL_REGEX_PATTERN.matcher(email).matches(), "邮箱格式不正确");
    }

    /**
     * 校验手机格式
     * 大陆手机号
     *
     * @param mobile 手机号
     */
    public static void checkMobile(String mobile) {
        Assert.notNull(mobile, "手机号不能为空");
        Assert.isTrue(MOBILE_REGEX_PATTERN.matcher(mobile).matches(), "手机号格式不正确");
    }

    /**
     * 校验密码
     *
     * @param password 密码
     */
    public static void checkPassword(String password) {
        Assert.notNull(password, "密码不能为空");
        Assert.isTrue(PASSWORD_REGEX_PATTERN.matcher(password).matches(), "密码为6-20位字母数字_!@#$%^&*,.?:;组成");
    }
}
