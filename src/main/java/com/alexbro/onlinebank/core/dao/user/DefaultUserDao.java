package com.alexbro.onlinebank.core.dao.user;

import com.alexbro.onlinebank.core.dao.util.SessionProvider;
import com.alexbro.onlinebank.core.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class DefaultUserDao implements UserDao{

    private static final String GET_USER_BY_USERNAME_QUERY ="SELECT u FROM User u WHERE u.username = :username";
    private static final String GET_USER_BY_ID_QUERY ="SELECT u FROM User u WHERE u.code = :code";

    @Resource
    private SessionProvider sessionProvider;

    @Transactional
    @Override
    public Optional<User> findByUsername(String username) {
        return sessionProvider.getSession().createQuery(GET_USER_BY_USERNAME_QUERY, User.class).setParameter("username", username).
                uniqueResultOptional();
    }

    @Override
    public Optional<User> findByCode(String code) {
        return sessionProvider.getSession().createQuery(GET_USER_BY_ID_QUERY, User.class).setParameter("code", code).
                uniqueResultOptional();
    }
}
