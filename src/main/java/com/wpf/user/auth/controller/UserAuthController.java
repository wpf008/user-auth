package com.wpf.user.auth.controller;

import com.wpf.user.auth.model.LoginInfo;
import com.wpf.user.auth.service.UserAuthService;
import com.wpf.user.auth.utils.ApiException;
import com.wpf.user.auth.utils.CodeEnum;
import com.wpf.user.auth.utils.Response;
import com.wpf.user.auth.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:46
 * @ClassName: user-auth
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {


    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody LoginInfo loginInfo) {
        try {
            String token = userAuthService.doLogin(loginInfo);
            return ResponseBuilder.buildSuccessResponse(token);
        }catch (ApiException e){
            return ResponseBuilder.buildFailResponse(e);
        }catch (Exception e){
            return ResponseBuilder.buildFailResponse(new ApiException(CodeEnum.NeedLogin));
        }
    }


    @GetMapping("/needlogin")
    public Response needlogin() {
        return ResponseBuilder.buildFailResponse(CodeEnum.NeedLogin);
    }

    @GetMapping("/unauthor")
    public Response unauthor() {
        return ResponseBuilder.buildFailResponse(CodeEnum.AuthFaild);
    }
}
