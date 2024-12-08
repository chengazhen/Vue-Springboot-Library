package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private JwtInterceptor jwtInterceptor;

     // 定义白名单路径
     private static final String[] WHITE_LIST = {
      "/auth/login",
      "/auth/register",
      "/swagger-ui.html",
      "/swagger-ui/**",
      "/v3/api-docs/**",
      "/swagger-resources/**",
      "/swagger-resources",
      "/swagger-resources/configuration/ui",
      "/swagger-resources/configuration/security",
      "/webjars/**",
      "/v3/api-docs/**",
      "/error",
      "/user/login",
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(WHITE_LIST);
    }
} 