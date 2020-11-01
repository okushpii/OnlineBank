package com.alexbro.onlinebank.config.context;

import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.interceptor.AuthInterceptor;
import com.alexbro.onlinebank.webfront.interceptor.AdminRoleInterceptor;
import com.alexbro.onlinebank.webfront.interceptor.UserRoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;

import javax.annotation.Resource;

@Configuration
public class WebContext implements WebMvcConfigurer {

    @Resource
    private AuthInterceptor authInterceptor;
    @Resource
    private AdminRoleInterceptor adminRoleInterceptor;
    @Resource
    private UserRoleInterceptor userRoleInterceptor;
    @Resource
    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(userRoleInterceptor).addPathPatterns(getUserMappings());
        registry.addInterceptor(adminRoleInterceptor).addPathPatterns(getAdminMappings());
        registry.addInterceptor(authInterceptor).addPathPatterns(getUserMappings());
        registry.addInterceptor(authInterceptor).addPathPatterns(getAdminMappings());
    }

    private List<String> getUserMappings() {
        return List.of(WebConstants.Mapping.USER + "/**", WebConstants.Mapping.TRANSFER,
                WebConstants.Mapping.ACCOUNT + WebConstants.Mapping.TRANSFER, WebConstants.Mapping.EXCHANGE_STEP_ONE,
                WebConstants.Mapping.EXCHANGE_STEP_TWO, WebConstants.Mapping.EXCHANGE_STEP_THREE,
                WebConstants.Mapping.ACCOUNT + WebConstants.Mapping.EXCHANGE);
    }

    private List<String> getAdminMappings() {
        return List.of(WebConstants.Mapping.ADMIN + "/**");

    }
}
