package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") // 拦截所有请求
@Slf4j
public class DemoFilter implements  Filter{

    // 初始化方法,在过滤器加载时调用
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法.....");
    }

    // 过滤方法,拦截到请求之后,调用该方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter 拦截到请求.....，,放行前....");
        // 放行请求
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("doFilter 拦截到请求.....，,放行后....");
    }

    // 销毁方法,在过滤器卸载时调用
    @Override
    public void destroy() {
        log.info("destroy 销毁方法.....");
    }
}

