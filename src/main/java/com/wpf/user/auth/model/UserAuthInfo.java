package com.wpf.user.auth.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:17
 * @ClassName: user-auth
 * @Description:
 */
@Data
public class UserAuthInfo implements Serializable {
    private String id;
    private String salt;
    private String username;
    private String token;
    private String password;
}
