package com.alexbro.onlinebank.core.strategy;

import java.util.Optional;

public interface ConfigurationFetchingStrategy {

    Optional<String> fetch(String key);
}
