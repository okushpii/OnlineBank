package com.alexbro.onlinebank.core.dao.user;

import com.alexbro.onlinebank.core.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByUsername(String username);

    Optional<User> findByCode(String code);

    void register(User user);
}
