package com.alexbro.onlinebank.core.service.operation;

import com.alexbro.onlinebank.core.dao.operation.OperationDao;
import com.alexbro.onlinebank.core.entity.Operation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultOperationService implements OperationService {

    @Resource
    private OperationDao operationDao;

    @Override
    public void save(Operation operation) {
        operationDao.save(operation);
    }
}
