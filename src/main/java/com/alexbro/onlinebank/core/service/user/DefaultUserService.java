package com.alexbro.onlinebank.core.service.user;

import com.alexbro.onlinebank.core.dao.user.UserDao;
import com.alexbro.onlinebank.core.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public Optional<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public Optional<User> getByCode(String code) {
        return userDao.getByCode(code);
    }
}
