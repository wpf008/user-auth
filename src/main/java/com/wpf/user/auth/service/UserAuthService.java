package com.wpf.user.auth.service;

import com.wpf.user.auth.model.LoginInfo;
import com.wpf.user.auth.model.UserAuthInfo;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:33
 * @ClassName: user-auth
 * @Description:
 */
public interface UserAuthService {

    UserAuthInfo findByUserName(String userName);

    String doLogin(LoginInfo loginInfo);
}
