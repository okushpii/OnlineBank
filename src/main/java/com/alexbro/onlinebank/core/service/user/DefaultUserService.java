package com.alexbro.onlinebank.core.service.user;

import com.alexbro.onlinebank.core.dao.user.UserDao;
import com.alexbro.onlinebank.core.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Optional<User> findByCode(String code) {
        return userDao.findByCode(code);
    }

    @Override
    public void register(User user) {
        userDao.register(user);
    }
}
