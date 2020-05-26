package com.alexbro.onlinebank.auth.view;

import com.alexbro.onlinebank.auth.AuthConstants;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class AuthTag extends RequestContextAwareTag {

    @Override
    protected int doStartTagInternal() {
        AuthFacade authFacade = (AuthFacade) WebApplicationContextUtils
                .getRequiredWebApplicationContext(pageContext.getServletContext())
                .getBean(AuthConstants.AUTH_FACADE);

        AuthData authData = (AuthData) pageContext.getSession().getAttribute(AuthConstants.AUTH_DATA_ATTRIBUTE);
        if (authFacade.isAuthorized(authData)) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }

}


