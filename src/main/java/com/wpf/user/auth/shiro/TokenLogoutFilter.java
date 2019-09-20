package com.wpf.user.auth.shiro;

import com.wpf.user.auth.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Wang pengfei
 * @date 2019/9/20 16:24
 * @ClassName: TokenLogoutFilter
 * @Description:
 */
public class TokenLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authorization = httpServletRequest.getHeader(Constants.AUTHORIZATION);
            StringRedisTemplate stringRedisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
            String loginKey = String.format(Constants.AUTH_TOKEN_KEY, authorization);
            if(null == authorization){//请求时header没带token
                ResponseBuilder.builderLoginFail(response, CodeEnum.MissingAuthorization);
                return false;
            }
            if(!stringRedisTemplate.hasKey(loginKey)){//token在redis里不存在
                ResponseBuilder.builderLoginFail(response, CodeEnum.AuthorizationNoExists);
                return false;
            }
            stringRedisTemplate.delete(loginKey);
            Subject subject = getSubject(request, response);
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            MyRealm myRealm = (MyRealm) securityManager.getRealms().iterator().next();
            PrincipalCollection principals = subject.getPrincipals();
            myRealm.clearCache(principals);
            subject.logout();
            issueRedirect(request, response, getRedirectUrl(request, response, subject));
        } catch (Exception e) {
            ResponseBuilder.builderLoginFail(response, CodeEnum.LoginError);
        }
        return false;
    }
}
