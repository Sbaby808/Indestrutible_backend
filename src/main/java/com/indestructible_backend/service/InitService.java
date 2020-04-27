package com.indestructible_backend.service;

import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.domain.DatabaseAndTables;

/**
 * 初始化连接Service
 *
 * @Author Sbaby
 * @Date 2020/03/09 14:56
 * @Version 1.0
 */
public interface InitService {

    /**
     * 测试连接
     * @param dbInfo
     * @return
     */
    public boolean testConnection(DBInfo dbInfo, String dataSource) throws Exception;

    /**
     * 获取数据库列表
     * @return
     */
    public DatabaseAndTables getDatabaseAndTables();



}
