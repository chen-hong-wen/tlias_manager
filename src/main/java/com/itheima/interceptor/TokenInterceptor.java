package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * token拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*//1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断是否是登录请求
        if (requestURI.equals("/login")) {
            log.info("登录请求，放行");
            return true;
        }*/

        //3.获取请求头中的token字符串
        String token = request.getHeader("token");

        //4.判断token字符串是否存在，若不存在，直接返回错误信息
        if (token == null || token.isEmpty()) {
            log.error("token字符串为空,响应401错误");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5.token存在，校验令牌
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.error("token校验失败,响应401错误", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6.若校验通过，放行
        log.info("token校验通过,放行");
        return true;
    }
}
