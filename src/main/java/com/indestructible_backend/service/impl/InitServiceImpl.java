package com.indestructible_backend.service.impl;

import com.indestructible_backend.DataSourceHelper.ChangeDataSourceHelper;
import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.mapper.DatabaseStructureDao;
import com.indestructible_backend.service.InitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public boolean testConnection(DBInfo dbInfo) throws Exception {
        if(ChangeDataSourceHelper.changeDataSource(dbInfo)) {
            List<String> lst = databaseStructureDao.databaseList();
            return lst.size() > 0;
        } else {
            return false;
        }
    }
}
