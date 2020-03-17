package com.indestructible_backend.service;

/**
 * @Author Sbaby
 * @Date 2020/03/18 1:04
 * @Version 1.0
 */
public interface DatabaseService {

    /**
     * 创建数据库
     * @param dataSource
     * @return
     */
    public int newDatabase(String dbName, String charset, String collate, String dataSource);

}
