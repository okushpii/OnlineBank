package com.alexbro.onlinebank.core.dao.config;

import com.alexbro.onlinebank.core.entity.config.Configuration;

import java.util.Optional;

public interface ConfigurationDao {

    Optional<Configuration> find(String key);
}
