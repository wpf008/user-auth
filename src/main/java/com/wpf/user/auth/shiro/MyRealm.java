package com.wpf.user.auth.shiro;

import com.wpf.user.auth.model.UserAuthInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;

/**
 * @author Wang pengfei
 * @date 2019/9/20 16:11
 * @ClassName: MyRealm
 * @Description:
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        info.setStringPermissions(new HashSet<>());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        MyAuthenticationToken myAuthenticationToken = (MyAuthenticationToken)authenticationToken;
        UserAuthInfo user =myAuthenticationToken.getUserAuthInfo();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return authenticationInfo;
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyAuthenticationToken;
    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
