package com.indestructible_backend.service;

import com.indestructible_backend.domain.NewDbInfo;

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

}
