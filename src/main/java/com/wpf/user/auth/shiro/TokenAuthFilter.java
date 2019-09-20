package com.wpf.user.auth.shiro;

import com.alibaba.fastjson.JSONObject;
import com.wpf.user.auth.model.UserAuthInfo;
import com.wpf.user.auth.utils.CodeEnum;
import com.wpf.user.auth.utils.Constants;
import com.wpf.user.auth.utils.ResponseBuilder;
import com.wpf.user.auth.utils.SpringContextUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author Wang pengfei
 * @date 2019/9/20 16:16
 * @ClassName: TokenAuthcFilte
 * @Description:
 */
public class TokenAuthFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        if (servletRequest instanceof HttpServletRequest) {
            if (((HttpServletRequest) servletRequest).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse)  {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String authToken = httpRequest.getHeader(Constants.AUTHORIZATION);  //获取token
        try {
            if (null != authToken && !StringUtils.isEmpty(authToken)) {
                StringRedisTemplate stringRedisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
                String loginKey = String.format(Constants.AUTH_TOKEN_KEY, authToken);
                if (stringRedisTemplate.hasKey(loginKey)) {
                    String userInfo = stringRedisTemplate.opsForValue().get(loginKey);
                    stringRedisTemplate.expire(loginKey,Constants.DEFAULT_LOGIN_TIME, TimeUnit.MINUTES);
                    UserAuthInfo userAuthInfo = JSONObject.parseObject(userInfo, UserAuthInfo.class);
                    MyAuthenticationToken token = new MyAuthenticationToken();
                    token.setToken(authToken);
                    token.setUserAuthInfo(userAuthInfo);
                    getSubject(servletRequest, servletResponse).login(token);
                } else   {//token 不出存在
                    ResponseBuilder.builderLoginFail(servletResponse,CodeEnum.LoginError);
                    return false;
                }
            }else {  //token 为空
                ResponseBuilder.builderLoginFail(servletResponse,CodeEnum.MissingAuthorization);
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
}
