package com.alexbro.onlinebank.core.dao.operation;

import com.alexbro.onlinebank.core.dao.util.SessionProvider;
import com.alexbro.onlinebank.core.entity.Operation;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class DefaultOperationDao implements OperationDao {

    @Resource
    private SessionProvider sessionProvider;


    @Override
    public void save(Operation operation) {
        sessionProvider.getSession().save(operation);
    }
}
