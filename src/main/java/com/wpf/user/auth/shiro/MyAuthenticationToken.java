package com.wpf.user.auth.shiro;

import com.wpf.user.auth.model.UserAuthInfo;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Wang pengfei
 * @date 2019/9/20 16:12
 * @ClassName: MyAuthenticationToken
 * @Description:
 */
@Data
public class MyAuthenticationToken implements AuthenticationToken {
    private String token;
    private UserAuthInfo userAuthInfo;

    @Override
    public Object getPrincipal() {
        return this.userAuthInfo.getPassword();
    }

    @Override
    public Object getCredentials() {
        return this.getUserAuthInfo().getPassword();
    }
}
