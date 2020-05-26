package com.alexbro.onlinebank.core.dao.user;

import com.alexbro.onlinebank.core.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getByUsername(String username);

    Optional<User> getByCode(String code);
}
