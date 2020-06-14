package com.alexbro.onlinebank.core.dao.config.impl;

import com.alexbro.onlinebank.core.dao.config.ConfigurationDao;
import com.alexbro.onlinebank.core.entity.config.Configuration;
import com.alexbro.onlinebank.core.dao.util.SessionProvider;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class DefaultConfigurationDao implements ConfigurationDao {

    private static final String FIND_BY_KEY_QUERY = "SELECT c FROM Configuration c WHERE c.key = :key";

    @Resource
    private SessionProvider sessionProvider;

    @Override
    public Optional<Configuration> find(String key) {
        return Optional.ofNullable(sessionProvider.getSession().createQuery(FIND_BY_KEY_QUERY, Configuration.class)
                .setParameter("key", key)
                .uniqueResult());
    }
}
