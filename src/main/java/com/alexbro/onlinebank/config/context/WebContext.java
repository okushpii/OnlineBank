package com.alexbro.onlinebank.config.context;

import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.annotation.Resource;

@Configuration
public class WebContext implements WebMvcConfigurer {

    @Resource
    private AuthInterceptor authInterceptor;
    @Resource
    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(authInterceptor).addPathPatterns(WebConstants.Mapping.USER + "/**");
    }

}
