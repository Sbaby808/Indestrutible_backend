package com.indestructible_backend.service.impl;

import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.mapper.DatabaseDao;
import com.indestructible_backend.service.DatabaseService;
import com.indestructible_backend.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;

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

    @Override
    public void dropTable(String dbName, String tbName) {
        databaseDao.dropTable(dbName, tbName);
    }

    @Override
    public File exportDatabase(String dbName, String fileName) throws Exception {
        if(!"".equals(fileName)) {
            StringBuffer content = new StringBuffer();
            content.append("DROP TABLE IF EXISTS `testTable`;").append("\r\n");
            content.append("CREATE TABLE `testTable`(").append("\r\n");
            content.append("`testColumn` varchar(255) NOT NULL,").append("\r\n");
            content.append(") ENGINE=InnoDB DEFAULT CHARSET=latin1;").append("\r\n");
            File file = FileUtil.createAndWriteSQLFile(fileName, content);
            return file;
        } else {
            throw new RuntimeException("fileName can not be null!");
        }
    }
}
