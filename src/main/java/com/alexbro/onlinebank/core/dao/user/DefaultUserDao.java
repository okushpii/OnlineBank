package com.alexbro.onlinebank.core.dao.user;

import com.alexbro.onlinebank.core.dao.common.util.SessionProvider;
import com.alexbro.onlinebank.core.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class DefaultUserDao implements UserDao{

    private static final String GET_USER_BY_USERNAME_QUERY ="SELECT u FROM User u WHERE u.username = :username";

    @Resource
    private SessionProvider sessionProvider;

    @Transactional
    @Override
    public Optional<User> getUserByLogin(String username) {
        return sessionProvider.getSession().createQuery(GET_USER_BY_USERNAME_QUERY, User.class).setParameter("username", username).
                uniqueResultOptional();
    }

}
