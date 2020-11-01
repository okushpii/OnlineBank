package com.alexbro.onlinebank.webfront.urlresolver;

import com.alexbro.onlinebank.core.entity.Role;

public interface UrlResolver {

    String resolve(Role role);
}
