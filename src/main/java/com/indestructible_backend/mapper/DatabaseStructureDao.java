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
public interface DatabaseStructureDao {

    @Select("select TABLE_NAME, TABLE_SCHEMA from TABLES")
    List<Map> databaseAndTableList();

    @Select("show databases")
    List<String> databaseList();

}
