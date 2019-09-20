package com.wpf.user.auth.controller;

import com.wpf.user.auth.utils.Response;
import com.wpf.user.auth.utils.ResponseBuilder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wang pengfei
 * @date 2019/9/20 16:30
 * @ClassName: HiController
 * @Description:
 */
@RestController
@RequestMapping("/api/v1")
public class HiController {

    @GetMapping("/test")
    public Response test(@RequestHeader("Authorization")String authorization){
        return ResponseBuilder.buildSuccessResponse("登录用户");
    }
}
