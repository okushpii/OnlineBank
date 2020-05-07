package com.alexbro.onlinebank.core.dao.common.util;

import org.hibernate.Session;

public interface SessionProvider {

    Session getSession();
}
