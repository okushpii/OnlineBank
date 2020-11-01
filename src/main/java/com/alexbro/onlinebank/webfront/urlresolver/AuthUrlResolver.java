package com.alexbro.onlinebank.webfront.urlresolver;

import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthUrlResolver implements UrlResolver {

    @Override
    public String resolve(Role role) {
        Map<Role, String> urlMap = new HashMap<>();
        urlMap.put(Role.USER, WebConstants.Mapping.USER);
        urlMap.put(Role.EMPLOYEE, WebConstants.Mapping.ADMIN);
        return urlMap.get(role);
    }
}
