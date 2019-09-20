package com.wpf.user.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wpf.user.auth.dao.UserAuthDao;
import com.wpf.user.auth.model.LoginInfo;
import com.wpf.user.auth.model.UserAuthInfo;
import com.wpf.user.auth.service.UserAuthService;
import com.wpf.user.auth.utils.ApiException;
import com.wpf.user.auth.utils.CodeEnum;
import com.wpf.user.auth.utils.Constants;
import com.wpf.user.auth.utils.EndecryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:33
 * @ClassName: user-auth
 * @Description:
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${user.login.time}")
    private long loginTime;

    @Override
    public UserAuthInfo findByUserName(String userName) {
        return userAuthDao.findByUserName(userName);
    }


    @Override
    public String doLogin(LoginInfo loginInfo) {
        try {
            UserAuthInfo userAuthInfo = userAuthDao.findByUserName(loginInfo.getLoginName());
            if (null == userAuthInfo) {
                throw new ApiException(CodeEnum.UserNoExists);
            }
            if (!EndecryptUtils.checkPwd(loginInfo.getPassword(), userAuthInfo.getPassword(), userAuthInfo.getSalt())) {
                throw new ApiException(CodeEnum.UserOrPWDWrong);
            }
            String token = EndecryptUtils.generateToken(userAuthInfo.getUsername());
            String loginKey = String.format(Constants.AUTH_TOKEN_KEY,token);
            String loginValue =  JSON.toJSONString(userAuthInfo, SerializerFeature.DisableCircularReferenceDetect);
            stringRedisTemplate.opsForValue().set(loginKey,loginValue,loginTime,TimeUnit.MINUTES);
            return token;
        } catch (Exception e) {
            throw e;
        }
    }
}
