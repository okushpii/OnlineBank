package com.alexbro.onlinebank.core.service.config;

import java.util.Optional;

public interface ConfigurationService {

    Optional<String> find(String key);

    String findRequired(String key);
}
