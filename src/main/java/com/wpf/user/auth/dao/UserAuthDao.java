package com.wpf.user.auth.dao;

import com.wpf.user.auth.model.UserAuthInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:20
 * @ClassName: user-auth
 * @Description:
 */
@Repository
public interface UserAuthDao {

    @Select("select * from t_user where username=#{userName}")
    UserAuthInfo findByUserName(String userName);
}
