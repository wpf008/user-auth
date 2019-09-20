package com.wpf.user.auth.utils;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:22
 * @ClassName: user-auth
 * @Description:
 */
public class Constants {


    /**
     * token存到redis的login key
     */
    public final static String AUTH_TOKEN_KEY ="AUTH_TOKEN_%s";

    /**
     * token
     */
    public final static String AUTHORIZATION ="Authorization";

    /**
     * 默认的登录时长
     */
    public final static long DEFAULT_LOGIN_TIME = 60;
}
