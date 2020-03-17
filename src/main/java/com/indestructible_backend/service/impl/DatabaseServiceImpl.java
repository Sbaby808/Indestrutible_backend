package com.indestructible_backend.service.impl;

import com.indestructible_backend.mapper.DatabaseDao;
import com.indestructible_backend.service.DatabaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author Sbaby
 * @Date 2020/03/18 1:05
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DatabaseServiceImpl implements DatabaseService {

    @Resource
    DatabaseDao databaseDao;

    @Override
    public int newDatabase(String dbName, String charset, String collate, String dataSource) {
        if(charset == null || " ".equals(charset)) {
            return databaseDao.createNewDatabaseC(dbName);
        } else if(collate == null || " ".equals(collate)) {
            return databaseDao.createNewDatabaseB(dbName, charset);
        } else {
            return databaseDao.createNewDatabaseA(dbName, charset, collate);
        }
    }
}
