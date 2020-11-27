package com.alexbro.onlinebank.core.service.user;

import com.alexbro.onlinebank.core.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByCode(String code);

    void register(User user);

    Optional<User> findByAccount(String accountCode);
}
