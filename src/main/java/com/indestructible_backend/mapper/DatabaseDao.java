package com.indestructible_backend.mapper;

import com.indestructible_backend.domain.TableAttribute;
import com.indestructible_backend.domain.TableStructure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @Author Sbaby
 * @Date 2020/03/18 1:07
 * @Version 1.0
 */
@Mapper
public interface DatabaseDao {

    @Update("CREATE DATABASE `${dbName}` CHARACTER SET #{charset} COLLATE #{collate}")
    int createNewDatabaseA(@Param("dbName") String dbName, @Param("charset") String charset, @Param("collate") String collate);

    @Update("CREATE DATABASE `${dbName}` CHARACTER SET #{charset}")
    int createNewDatabaseB(@Param("dbName") String dbName, @Param("charset") String charset);

    @Update("CREATE DATABASE `${dbName}`")
    int createNewDatabaseC(@Param("dbName") String dbName);

    @Update("DROP DATABASE `${dbName}`")
    void dropDatabase(@Param("dbName") String dbName);

    @Update("DROP TABLE ${dbName}.${tbName}")
    void dropTable(@Param("dbName") String dbName, @Param("tbName") String tbName);

    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,ENGINE,TABLE_COLLATION FROM TABLES WHERE TABLE_SCHEMA = #{dbName}")
    List<TableAttribute> tableList(@Param("dbName") String dbName);

    @Update("USE ${dbName}")
    void useDatabase(@Param("dbName") String dbName);

    @Select("DESC ${tbName}")
    List<TableStructure> tableStructures(@Param("tbName") String tbName);

    @Select("SELECT * FROM ${tbName}")
    List<Map<String, String>> tableData(@Param("tbName") String tbName);

}
