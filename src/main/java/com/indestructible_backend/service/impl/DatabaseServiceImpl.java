package com.indestructible_backend.service.impl;

import com.indestructible_backend.domain.NewDbInfo;
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
    public int newDatabase(NewDbInfo newDbInfo) {
        if(" ".equals(newDbInfo.getCharset())) {
            return databaseDao.createNewDatabaseC(newDbInfo.getDbName());
        } else if(" ".equals(newDbInfo.getCollate())) {
            return databaseDao.createNewDatabaseB(newDbInfo.getDbName(), newDbInfo.getCharset());
        } else {
            return databaseDao.createNewDatabaseA(newDbInfo.getDbName(), newDbInfo.getCharset(), newDbInfo.getCollate());
        }
    }

    @Override
    public void dropDatabase(String dbName) {
        databaseDao.dropDatabase(dbName);
    }
}
