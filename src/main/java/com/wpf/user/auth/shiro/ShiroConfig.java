package com.wpf.user.auth.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @author Wang pengfei
 * @date 2019/9/20 16:10
 * @ClassName: ShiroConfig
 * @Description:
 */
@Configuration
public class ShiroConfig {
    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myRealm());// 将自定义 Realm 加进来
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/api/v1/auth/needlogin");//没用登录的，重定向url
        bean.setUnauthorizedUrl("/api/v1/auth/unauthor"); //没用权限，重定向url

        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new TokenAuthFilter());
        filters.put("logout", tokenLogoutFilter());
        bean.setFilters(filters);

        LinkedHashMap<String, String> chains = new LinkedHashMap<>();
        chains.put("/api/v1/auth/login", "anon");
        chains.put("/api/v1/auth/unauthor", "anon");
        chains.put("/api/v1/auth/needlogin", "anon");
        chains.put("/api/**", "authc");
        chains.put("/logout", "logout");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    public TokenLogoutFilter tokenLogoutFilter() {
        TokenLogoutFilter tokenLogoutFilter = new TokenLogoutFilter();
        tokenLogoutFilter.setRedirectUrl("/api/v1/auth/needlogin");
        return tokenLogoutFilter;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
