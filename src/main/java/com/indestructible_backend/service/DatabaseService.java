package com.indestructible_backend.service;

import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.domain.vo.TableStructureVo;

import java.io.File;
import java.util.List;

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

    /**
     * 获取表结构信息
     */
    public List<TableStructureVo> tableStructure(String dbName, String tbName);

    /**
     * 删除列
     */
    public List<TableStructureVo> dropColumn(String dbName, String tbName, String columnName);

    public List<TableStructureVo> addColumn(String dbName, String tbName, TableStructureVo tableStructureVo);

}
