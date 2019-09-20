package com.wpf.user.auth.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:24
 * @ClassName: user-auth
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int code;
    private String message;
    private Object data;
}
