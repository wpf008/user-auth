package com.wpf.user.auth.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Wang pengfei
 * @date 2019/9/20 15:25
 * @ClassName: user-auth
 * @Description:
 */
public class ResponseBuilder {
    public static Response buildSuccessResponse(Object data) {
        return buildSuccessResponse(data, "操作成功");
    }

    public static Response buildSuccessResponse(Object data, String message) {
        return new Response(200, message, data);
    }

    public static Response buildFailResponse(int code, String messag) {
        return new Response(code, messag, null);
    }

    public static Response buildFailResponse(String messag) {
        return buildFailResponse(201, messag);
    }

    public static Response buildFailResponse(CodeEnum codeEnum) {
        return buildFailResponse(codeEnum.getCode(), codeEnum.getDescription());
    }

    public static Response buildFailResponse(ApiException e){
        return buildFailResponse(e.getCode(),e.getMessage());
    }


    public static void builderLoginFail(ServletResponse servletResponse,CodeEnum codeEnum){
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setCharacterEncoding(Charset.forName("utf-8").name());
        httpResponse.setContentType("application/json");
        try {
            httpResponse.getWriter().write(JSON.toJSONString(buildFailResponse(codeEnum)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
