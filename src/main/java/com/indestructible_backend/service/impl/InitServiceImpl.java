package com.indestructible_backend.service.impl;

import com.indestructible_backend.DataSourceHelper.ChangeDataSourceHelper;
import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.domain.DatabaseAndTables;
import com.indestructible_backend.mapper.InitDao;
import com.indestructible_backend.service.InitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 测试数据库是否能正确连接
 *
 * @Author Sbaby
 * @Date 2020/03/09 14:59
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InitServiceImpl implements InitService {

    @Resource
    InitDao initDao;

    @Override
    public boolean testConnection(DBInfo dbInfo, String dataSource) throws Exception {
        if(ChangeDataSourceHelper.changeDataSource(dbInfo, dataSource)) {
            List<String> lst = initDao.databaseList();
            return lst.size() > 0;
        } else {
            return false;
        }
    }

    @Override
    public DatabaseAndTables getDatabaseAndTables() {
        List<Map> lst = initDao.databaseAndTableList();
        DatabaseAndTables result = new DatabaseAndTables();
        for (Map map : lst) {
            String databaseName = map.get("TABLE_SCHEMA") + "";
            String tableName = map.get("TABLE_NAME") + "";
            result.addItem(databaseName, tableName);
        }
        return result;
    }
}
