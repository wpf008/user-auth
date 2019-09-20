package com.wpf.user.auth.model;

import lombok.Data;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:19
 * @ClassName: user-auth
 * @Description:
 */
@Data
public class LoginInfo {
    private String loginName;
    private String password;
}
