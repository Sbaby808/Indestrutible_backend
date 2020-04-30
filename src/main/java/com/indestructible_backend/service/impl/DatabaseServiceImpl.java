package com.indestructible_backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.domain.TableAttribute;
import com.indestructible_backend.domain.TableStructure;
import com.indestructible_backend.domain.vo.TableStructureVo;
import com.indestructible_backend.mapper.DatabaseDao;
import com.indestructible_backend.service.DatabaseService;
import com.indestructible_backend.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * @Author Sbaby
 * @Date 2020/03/18 1:05
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DatabaseServiceImpl implements DatabaseService {

    @Resource
    DatabaseDao databaseDao;

    @Override
    public int newDatabase(NewDbInfo newDbInfo) {
        if(" ".equals(newDbInfo.getCharset())) {
            return databaseDao.createNewDatabaseC(newDbInfo.getDbName());
        } else if(" ".equals(newDbInfo.getCollate())) {
            return databaseDao.createNewDatabaseB(newDbInfo.getDbName(), newDbInfo.getCharset());
        } else {
            return databaseDao.createNewDatabaseA(newDbInfo.getDbName(), newDbInfo.getCharset(), newDbInfo.getCollate());
        }
    }

    @Override
    public void dropDatabase(String dbName) {
        databaseDao.dropDatabase(dbName);
    }

    @Override
    public void dropTable(String dbName, String tbName) {
        databaseDao.dropTable(dbName, tbName);
    }

    @Override
    public File exportDatabase(String dbName, String fileName) throws Exception {
        if(!"".equals(fileName)) {
            StringBuffer content = new StringBuffer();
            // 获取所有表名及表属性
            List<TableAttribute> tables = databaseDao.tableList(dbName);
            databaseDao.useDatabase(dbName);
            for(TableAttribute tableAttribute : tables) {
                // 获取表结构
                List<TableStructure> structures = databaseDao.tableStructures(tableAttribute.getTABLE_NAME());
                content.append("DROP TABLE IF EXISTS `").append(tableAttribute.getTABLE_NAME()).append("`;")
                        .append("\r\n").append("\r\n");
                content.append("CREATE TABLE `").append(tableAttribute.getTABLE_NAME()).append("` (")
                        .append("\r\n");
                List<String> primary_keys = new ArrayList<>();
                for(TableStructure tableStructure : structures) {
                    // 添加字段信息
                    content.append("`").append(tableStructure.getField()).append("` ")
                            .append(tableStructure.getType()).append(" ")
                            .append(tableStructure.getNull().equals("NO") ? "NOT NULL " : "")
                    .append(",").append("\r\n");
                    if("PRI".equals(tableStructure.getKey())) {
                        primary_keys.add(tableStructure.getField());
                    }
                }
                // 设置主键
                if(primary_keys.size() > 0) {
                    content.append("PRIMARY KEY (");
                    for(String primary_key : primary_keys) {
                        content.append("`").append(primary_key).append("`,");
                    }
                    content.deleteCharAt(content.length() - 1).append(")");
                    if(primary_keys.size() > 1) {
                        content.append(" USING BTREE");
                    }
                    content.append("\r\n");
                }
                content.append(") ENGINE=").append(tableAttribute.getENGINE()).append(" DEFAULT CHARSET=")
                        .append(tableAttribute.getTABLE_COLLATION().split("_")[0]).append(";")
                        .append("\r\n").append("\r\n");
                content.append("LOCK TABLES `").append(tableAttribute.getTABLE_NAME()).append("` WRITE;")
                        .append("\r\n");
                // 获取表数据
                List<Map<String, String>> data = databaseDao.tableData(tableAttribute.getTABLE_NAME());
                if(data.size() > 0) {
                    // 插入数据
                    content.append("INSERT INTO `").append(tableAttribute.getTABLE_NAME()).append("` values ");
                    for(Map<String, String> map : data) {
                        content.append("(");
                        for(TableStructure tableStructure : structures) {
                            if(tableStructure.getType().startsWith("char") || tableStructure.getType().startsWith("varchar")) {
                                content.append("'").append(String.valueOf(map.get(tableStructure.getField()))).append("',");
                            } else {
                                content.append(String.valueOf(map.get(tableStructure.getField()))).append(",");
                            }
                        }
                        // 删除最后一个逗号
                        content.deleteCharAt(content.length() - 1).append("),");
                    }
                    content.deleteCharAt(content.length() - 1).append(";").append("\r\n");
                }
                content.append("UNLOCK TABLES;").append("\r\n").append("\r\n");
            }
            File file = FileUtil.createAndWriteSQLFile(fileName, content);
            return file;
        } else {
            throw new RuntimeException("fileName can not be null!");
        }
    }

    @Override
    public List<TableStructureVo> tableStructure(String dbName, String tbName) {
        databaseDao.useDatabase(dbName);
        List<TableStructure> tableStructures = databaseDao.tableStructures(tbName);
        List<TableStructureVo> tableStructureVos = new ArrayList<>();
        for (TableStructure tableStructure : tableStructures) {
            tableStructureVos.add(new TableStructureVo(tableStructure));
        }
        return tableStructureVos;
    }

    @Override
    public List<TableStructureVo> dropColumn(String dbName, String tbName, String columnName) {
        databaseDao.useDatabase(dbName);
        databaseDao.dropColumn(tbName, columnName);
        return tableStructure(dbName, tbName);
    }

    @Override
    public List<TableStructureVo> addColumn(String dbName, String tbName, TableStructureVo tableStructureVo) {
        databaseDao.useDatabase(dbName);
        if(tableStructureVo.isKey()) {
            List<TableStructureVo> tableStructureVos = tableStructure(dbName, tbName);
            StringBuffer keys = new StringBuffer();
            for(TableStructureVo tableStructureVo_ : tableStructureVos) {
                if(tableStructureVo_.isKey()) {
                    keys.append(tableStructureVo_.getField()).append(",");
                }
            }
            keys.append(tableStructureVo.getField());
            databaseDao.addColumnWithKey(tbName, tableStructureVo.getField(), tableStructureVo.getType(),
                    tableStructureVo.getSize(), tableStructureVo.isNotnull() ? "NOT NULL" : "", keys.toString());
        } else {
            databaseDao.addColumnWithoutKey(tbName, tableStructureVo.getField(), tableStructureVo.getType(),
                    tableStructureVo.getSize(), tableStructureVo.isNotnull() ? "NOT NULL" : "");
        }
        List<TableStructureVo> list = tableStructure(dbName, tbName);
        return list;
    }

    @Override
    public List<TableStructureVo> modifyColumn(String dbName, String tbName, TableStructureVo oldTableStructureVo, TableStructureVo newTableStructureVo) {
        databaseDao.useDatabase(dbName);
        if(oldTableStructureVo.getField().equals(newTableStructureVo.getField())
                && oldTableStructureVo.isKey() == newTableStructureVo.isKey()) {
            // 不需要修改主键
            databaseDao.modifyColumnWithoutKeyAndField(
                    tbName, newTableStructureVo.getField(), newTableStructureVo.getType(),
                    newTableStructureVo.getSize(), newTableStructureVo.isNotnull() ? "NOT NULL" : "NULL");
        } else {
            List<TableStructureVo> tableStructureVos = tableStructure(dbName, tbName);
            StringBuffer keys = new StringBuffer();
            // 需要修改主键
            if(!oldTableStructureVo.getField().equals(newTableStructureVo.getField())) {
                // 需要修改字段名
                for(TableStructureVo tableStructureVo_ : tableStructureVos) {
                    if(tableStructureVo_.isKey() && !tableStructureVo_.getField().equals(oldTableStructureVo.getField())) {
                        keys.append(tableStructureVo_.getField()).append(",");
                    }
                }
                if(newTableStructureVo.isKey()) {
                    keys.append(newTableStructureVo.getField());
                }
                databaseDao.modifyColumnWithKeyAndField(tbName, oldTableStructureVo.getField(), newTableStructureVo.getField(), newTableStructureVo.getType(),
                        newTableStructureVo.getSize(), newTableStructureVo.isNotnull() ? "NOT NULL" : "NULL", keys.toString());
            } else {
                // 不需要修改字段名
                for(TableStructureVo tableStructureVo_ : tableStructureVos) {
                    if(tableStructureVo_.isKey()) {
                        if(tableStructureVo_.getField().equals(newTableStructureVo.getField()) && !newTableStructureVo.isKey()) {
                            // 取消key
                            break;
                        }
                        keys.append(tableStructureVo_.getField()).append(",");
                    }
                }
                // 设置为key
                if(!oldTableStructureVo.isKey() && newTableStructureVo.isKey()) {
                    keys.append(newTableStructureVo.getField()).append(",");
                }
                keys.deleteCharAt(keys.length() - 1);
                databaseDao.modifyColumnWithKey(tbName, newTableStructureVo.getField(), newTableStructureVo.getType(),
                        newTableStructureVo.getSize(), newTableStructureVo.isNotnull() ? "NOT NULL" : "NULL", keys.toString());
            }
        }
        List<TableStructureVo> list = tableStructure(dbName, tbName);
        return list;
    }

    @Override
    public Map<String, Object> showTableData(String dbName, String tbName, int pageNum, int pageSize, String ordField, boolean sort) {
        databaseDao.useDatabase(dbName);
        int count = databaseDao.countRows(tbName);
        if(!"".equals(ordField)) {
            String orderBy = ordField + (sort ? " desc" : " asc");
            PageHelper.startPage(pageNum, pageSize, ordField + orderBy);
        } else {
         PageHelper.startPage(pageNum, pageSize);
        }
        List<Map<String, String>> data = databaseDao.showTableData(tbName);
        List<String> columns = databaseDao.columnNames(dbName, tbName);
        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        result.put("columns", columns);
        result.put("total", count);
        return result;
    }

    @Override
    public Map<String, String> getDatabaseCharsetAndCollation(String dbName) {
        databaseDao.useDatabase(dbName);
        Map<String, String> map = new HashMap<>();
        map.put("charset", databaseDao.getDatabaseCharset().get(0).get("Value"));
        map.put("collation", databaseDao.getDatabaseCollation().get(0).get("Value"));
        return map;
    }

    @Override
    public void updateDatabseCharsetAndCollation(String dbName, String newCharset, String oldCharset, String newCollation, String oldCollation) {
        databaseDao.useDatabase(dbName);
        if(!" ".equals(newCharset)) {
            if(newCharset.equals(oldCharset) && !newCollation.equals(oldCollation)
             && !" ".equals(newCollation)) {
                // ALTER DATABASE `store` COLLATE 'utf8_esperanto_ci';
                databaseDao.updateCollation(dbName, newCollation);
            } else if(!newCharset.equals(oldCharset) && " ".equals(newCollation)) {
                // ALTER DATABASE `store` CHARACTER SET 'big5';
                databaseDao.updateCharset(dbName, newCharset);
            } else if(!newCharset.equals(oldCharset)) {
                // ALTER DATABASE `store` CHARACTER SET 'big5' COLLATE 'big5_chinese_ci';
                databaseDao.updateCharsetAndCollation(dbName, newCharset, newCollation);
            }
        }
    }

    @Override
    public File exportTable(String dbName, String tbName, String fileName) throws Exception {
        if(!"".equals(fileName)) {
            StringBuffer content = new StringBuffer();
            databaseDao.useDatabase(dbName);
            List<Map<String, String>> lms = databaseDao.getTableEngineAndCollation(dbName, tbName);
            String engine = lms.get(0).get("ENGINE");
            String collation = lms.get(0).get("TABLE_COLLATION");
            // 获取表结构
            List<TableStructure> structures = databaseDao.tableStructures(tbName);
            content.append("DROP TABLE IF EXISTS `").append(tbName).append("`;")
                    .append("\r\n").append("\r\n");
            content.append("CREATE TABLE `").append(tbName).append("` (")
                    .append("\r\n");
            List<String> primary_keys = new ArrayList<>();
            for(TableStructure tableStructure : structures) {
                // 添加字段信息
                content.append("`").append(tableStructure.getField()).append("` ")
                        .append(tableStructure.getType()).append(" ")
                        .append(tableStructure.getNull().equals("NO") ? "NOT NULL " : "")
                        .append(",").append("\r\n");
                if("PRI".equals(tableStructure.getKey())) {
                    primary_keys.add(tableStructure.getField());
                }
            }
            // 设置主键
            if(primary_keys.size() > 0) {
                content.append("PRIMARY KEY (");
                for(String primary_key : primary_keys) {
                    content.append("`").append(primary_key).append("`,");
                }
                content.deleteCharAt(content.length() - 1).append(")");
                if(primary_keys.size() > 1) {
                    content.append(" USING BTREE");
                }
                content.append("\r\n");
            }
            content.append(") ENGINE=").append(engine).append(" DEFAULT CHARSET=")
                    .append(collation.split("_")[0]).append(";")
                    .append("\r\n").append("\r\n");
            content.append("LOCK TABLES `").append(tbName).append("` WRITE;")
                    .append("\r\n");
            // 获取表数据
            List<Map<String, String>> data = databaseDao.tableData(tbName);
            if(data.size() > 0) {
                // 插入数据
                content.append("INSERT INTO `").append(tbName).append("` values ");
                for(Map<String, String> map : data) {
                    content.append("(");
                    for(TableStructure tableStructure : structures) {
                        if(tableStructure.getType().startsWith("char") || tableStructure.getType().startsWith("varchar")) {
                            content.append("'").append(String.valueOf(map.get(tableStructure.getField()))).append("',");
                        } else {
                            content.append(String.valueOf(map.get(tableStructure.getField()))).append(",");
                        }
                    }
                    // 删除最后一个逗号
                    content.deleteCharAt(content.length() - 1).append("),");
                }
                content.deleteCharAt(content.length() - 1).append(";").append("\r\n");
            }
            content.append("UNLOCK TABLES;").append("\r\n").append("\r\n");
            File file = FileUtil.createAndWriteSQLFile(fileName, content);
            return file;
        } else {
            throw new RuntimeException("fileName can not be null!");
        }
    }

    @Override
    public void newTable(String dbName, String tbName, String engine, String charset, List<TableStructureVo> list) {
        databaseDao.useDatabase(dbName);
        StringBuffer content = new StringBuffer();
        List<String> primary_keys = new ArrayList<>();
        for(TableStructureVo tableStructure : list) {
            // 添加字段信息
            content.append(tableStructure.getField()).append(" ")
                    .append(tableStructure.getType()).append(" ")
                    .append(tableStructure.isNotnull() ? "NOT NULL " : "")
                    .append(",");
            if(tableStructure.isKey()) {
                primary_keys.add(tableStructure.getField());
            }
        }
        // 设置主键
        if(primary_keys.size() > 0) {
            content.append("PRIMARY KEY (");
            for(String primary_key : primary_keys) {
                content.append(primary_key).append(",");
            }
            content.deleteCharAt(content.length() - 1).append("),");
        }
        // 删除最后一个逗号
        content.deleteCharAt(content.length() - 1);
        databaseDao.newTable(tbName, content.toString(), engine, charset);
    }
}
