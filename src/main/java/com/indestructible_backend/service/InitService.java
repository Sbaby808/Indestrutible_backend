package com.indestructible_backend.service;

import com.indestructible_backend.domain.DBInfo;

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
    public boolean testConnection(DBInfo dbInfo) throws Exception;

}
