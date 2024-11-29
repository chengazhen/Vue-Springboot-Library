package com.example.demo.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        
        // 允许的域名
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的请求方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        // 允许的请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 是否允许发送Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 预检请求的缓存时间
        response.setHeader("Access-Control-Max-Age", "3600");
        
        // 对于OPTIONS请求直接返回200
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
