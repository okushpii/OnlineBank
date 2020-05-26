package com.alexbro.onlinebank.core.dao.util;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class HibernateSessionProvider implements SessionProvider {

    private final EntityManager entityManager;

    @Autowired
    public HibernateSessionProvider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
