package com.indestructible_backend.service;

import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.domain.vo.TableStructureVo;

import java.io.File;
import java.util.List;
import java.util.Map;

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

    /**
     * 添加列
     */
    public List<TableStructureVo> addColumn(String dbName, String tbName, TableStructureVo tableStructureVo);

    /**
     * 修改列
     */
    public List<TableStructureVo> modifyColumn(String dbName, String tbName,
                                               TableStructureVo oldTableStructureVo, TableStructureVo newTableStructureVo);
    /**
     * 查看表数据
     */
    public Map<String, Object> showTableData(String dbName, String tbName, int pageNum, int pageSize, String ordField, boolean sort);

    /**
     * 查询数据库编码和排序规则
     */
    public Map<String, String> getDatabaseCharsetAndCollation(String dbName);

    /**
     * 修改数据库编码和排序规则
     */
    public void updateDatabseCharsetAndCollation(String dbName, String newCharset,
                                                 String oldCharset, String newCollation,
                                                 String oldCollation);

    /**
     * 导出表
     */
    public File exportTable(String dbName, String tbName, String fileName) throws Exception;

    /**
     * 创建表
     */
    public void newTable(String dbName, String tbName, String engine, String charset, List<TableStructureVo> list);
}
