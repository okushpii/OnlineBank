package com.alexbro.onlinebank.view.auth.tag;

import com.alexbro.onlinebank.facade.data.auth.AuthData;
import com.alexbro.onlinebank.facade.auth.AuthFacade;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class AuthTag extends RequestContextAwareTag {

    @Override
    protected int doStartTagInternal() {
        AuthFacade authFacade = (AuthFacade) WebApplicationContextUtils
                .getRequiredWebApplicationContext(pageContext.getServletContext())
                .getBean("authFacade");

        AuthData authData = (AuthData) pageContext.getSession().getAttribute("authData");
        if (authFacade.isAuthorized(authData)) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }

}


