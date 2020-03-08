package com.indestructible_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Sbaby
 * @Date 2020/03/08 16:37
 * @Version 1.0
 */
@Mapper
public interface DatabaseStructureDao {

    @Select("show databases")
    List<String> databaseList();

}
