package com.indestructible_backend.service.impl;

import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.domain.TableAttribute;
import com.indestructible_backend.domain.TableStructure;
import com.indestructible_backend.mapper.DatabaseDao;
import com.indestructible_backend.service.DatabaseService;
import com.indestructible_backend.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
