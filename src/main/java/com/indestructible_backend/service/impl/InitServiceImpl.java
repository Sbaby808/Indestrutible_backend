package com.indestructible_backend.service.impl;

import com.indestructible_backend.DataSourceHelper.ChangeDataSourceHelper;
import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;
import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.mapper.DatabaseStructureDao;
import com.indestructible_backend.service.InitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    DatabaseStructureDao databaseStructureDao;

    @Override
    public boolean testConnection(DBInfo dbInfo, String dataSource) throws Exception {
        if(ChangeDataSourceHelper.changeDataSource(dbInfo, dataSource)) {
            List<String> lst = databaseStructureDao.databaseList();
            return lst.size() > 0;
        } else {
            return false;
        }
    }

    @Override
    public Map<String, List<String>> getDatabaseAndTables() {
        List<Map> lst = databaseStructureDao.databaseAndTableList();
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for (Map map : lst) {
            String databaseName = map.get("TABLE_SCHEMA") + "";
            String tableName = map.get("TABLE_NAME") + "";
            if(!result.containsKey(databaseName)) {
                List<String> tables = new ArrayList<String>();
                tables.add(tableName);
                result.put(databaseName, tables);
            } else {
                result.get(databaseName).add(tableName);
            }
        }
        return result;
    }
}
