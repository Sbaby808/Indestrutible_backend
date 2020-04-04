package com.indestructible_backend.service;

import com.indestructible_backend.domain.NewDbInfo;

import java.io.File;

/**
 * @Author Sbaby
 * @Date 2020/03/18 1:04
 * @Version 1.0
 */
public interface DatabaseService {

    /**
     * 创建数据库
     */
    public int newDatabase(NewDbInfo newDbInfo);

    /**
     * 删除数据库
     */
    public void dropDatabase(String dbName);

    /**
     * 删除数据表
     */
    public void dropTable(String dbName, String tbName);

    /**
     * 导出数据库
     */
    public File exportDatabase(String dbName, String fileName) throws Exception;

}
