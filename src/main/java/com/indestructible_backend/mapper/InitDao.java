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

    @Select("select a.SCHEMA_NAME,b.TABLE_NAME" +
            " from information_schema.SCHEMATA a " +
            "left join " +
            "(select * from information_schema.TABLES" +
            " where TABLE_TYPE = 'BASE TABLE' " +
            "or TABLE_TYPE = 'SYSTEM VIEW') b" +
            " on a.SCHEMA_NAME = b.TABLE_SCHEMA;")
    List<Map> databaseAndTableList();

    @Select("show databases")
    List<String> databaseList();

}
