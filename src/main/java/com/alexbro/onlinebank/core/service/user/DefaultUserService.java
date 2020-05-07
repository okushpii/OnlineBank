package com.alexbro.onlinebank.core.service.user;

import com.alexbro.onlinebank.core.dao.user.UserDao;
import com.alexbro.onlinebank.core.entity.user.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userDao.getUserByLogin(username);
    }
}
