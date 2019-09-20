package com.wpf.user.auth.utils;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:49
 * @ClassName: user-auth
 * @Description:
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 3542451561250518138L;
    private int code;

    public ApiException() {
    }

    public ApiException(CodeEnum error) {
        super(error.getDescription());
        this.code = error.getCode();
    }



    public ApiException(CodeEnum error, String message) {
        super(message);
        this.code = error.getCode();
    }
    public int getCode() {
        return this.code;
    }
}
