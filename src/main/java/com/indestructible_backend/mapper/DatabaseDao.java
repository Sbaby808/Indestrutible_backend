package com.indestructible_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author Sbaby
 * @Date 2020/03/18 1:07
 * @Version 1.0
 */
@Mapper
public interface DatabaseDao {

    @Update("CREATE DATABASE ${dbName} CHARACTER SET #{charset} COLLATE #{collate}")
    int createNewDatabaseA(@Param("dbName") String dbName, @Param("charset") String charset, @Param("collate") String collate);

    @Update("CREATE DATABASE ${dbName} CHARACTER SET #{charset}")
    int createNewDatabaseB(@Param("dbName") String dbName, @Param("charset") String charset);

    @Update("CREATE DATABASE ${dbName}")
    int createNewDatabaseC(@Param("dbName") String dbName);


}
