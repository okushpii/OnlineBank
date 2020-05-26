package com.alexbro.onlinebank.config.context;

import com.alexbro.onlinebank.WebConstants;
import com.alexbro.onlinebank.auth.front.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebContext implements WebMvcConfigurer {

    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns(WebConstants.Mapping.USER_PAGE_MAPPING + "/**");
    }

}
