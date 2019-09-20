package com.wpf.user.auth.utils;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:23
 * @ClassName: user-auth
 * @Description:
 */
public enum CodeEnum {
    SystemError(10000, "System Error", "系统错误"),
    AuthFaild(10001, "Auth Faild", "认证失败"),
    NeedLogin(10002, "Need Login", "用户未登录"),
    UserNoExists(10003, "User Not Found", "用户不存在"),
    UserOrPWDWrong(10004, "User Or Password Is Wrong", "用户名或者密码错误"),
    LoginError(10005, "Login Error", "登录错误"),
    MissingAuthorization(10006, "Missing header Authorization ", "header没有token"),
    AuthorizationNoExists(10007, "Token Not Found", "Token不存在");
    private int code;
    private String message;
    private String description;

    private CodeEnum(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
