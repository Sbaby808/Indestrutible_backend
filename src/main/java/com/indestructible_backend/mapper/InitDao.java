package com.indestructible_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author Sbaby
 * @Date 2020/03/08 16:37
 * @Version 1.0
 */
@Mapper
public interface InitDao {

    @Select("select TABLE_NAME, TABLE_SCHEMA from TABLES where TABLE_TYPE = 'BASE TABLE' or TABLE_TYPE = 'SYSTEM VIEW'")
    List<Map> databaseAndTableList();

    @Select("show databases")
    List<String> databaseList();

}
