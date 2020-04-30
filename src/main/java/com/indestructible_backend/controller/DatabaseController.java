package com.indestructible_backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;
import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.domain.Response;
import com.indestructible_backend.domain.vo.TableStructureVo;
import com.indestructible_backend.service.DatabaseService;
import com.indestructible_backend.utils.DataSourceUtil;
import com.indestructible_backend.utils.SQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Sbaby
 * @Date 2020/03/18 0:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/database")
public class DatabaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseController.class);

    @Autowired
    DatabaseService databaseService;

    @PostMapping("/new_db")
    public Response newDatabase(@RequestBody NewDbInfo newDbInfo) {
        if(!DataSourceUtil.checkDataSource(newDbInfo.getDataSource())) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(newDbInfo.getDataSource());
            try {
                int result = databaseService.newDatabase(newDbInfo);
                return new Response().success(result);
            } catch (Exception e) {
                LOGGER.error("create database failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @DeleteMapping("/del_db")
    public Response dropDatabase(String dbName, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                databaseService.dropDatabase(dbName);
                return new Response().success();
            } catch (Exception e) {
                LOGGER.error("drop database failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @DeleteMapping("/del_tb")
    public Response dropTable(String dbName, String tbName, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                databaseService.dropTable(dbName, tbName);
                return new Response().success();
            } catch (Exception e) {
                LOGGER.error("drop table failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @GetMapping("/export_db")
    public Response exportDatabase(String dbName, String fileName, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                File file = databaseService.exportDatabase(dbName, fileName);
                return new Response().success(file);
            } catch (Exception e) {
                LOGGER.error("export database failed!", e);
                return new Response().failure(e.getMessage());
            }
        }
    }

    @GetMapping("/table_structure")
    public Response tableStructure(String dbName, String tbName, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                List<TableStructureVo> list = databaseService.tableStructure(dbName, tbName);
                return new Response().success(list);
            } catch (Exception e) {
                LOGGER.error("get table structure failed!", e);
                return new Response().failure(e.getMessage());
            }
        }
    }

    @DeleteMapping("/drop_column")
    public Response dropColumn(String dbName, String tbName, String columnName, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                List<TableStructureVo> list =  databaseService.dropColumn(dbName, tbName, columnName);
                return new Response().success(list);
            } catch (Exception e) {
                LOGGER.error("drop table column failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @PostMapping("/add_column")
    public Response addColumn(@RequestBody Map<String, String> map) {
        TableStructureVo tableStructureVo = JSONObject.parseObject(map.get("tableStructureVo"), TableStructureVo.class);
        String dbName = map.get("dbName");
        String tbName = map.get("tbName");
        String dataSource = map.get("dataSource");
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                List<TableStructureVo> list = databaseService.addColumn(dbName, tbName, tableStructureVo);
                return new Response().success(list);
            } catch (Exception e) {
                LOGGER.error("add table column failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @PostMapping("/modify_column")
    public Response modifyColumn(@RequestBody Map<String, String> map) {
        TableStructureVo oldTableStructureVo = JSONObject.parseObject(map.get("oldTableStructureVo"), TableStructureVo.class);
        TableStructureVo newTableStructureVo = JSONObject.parseObject(map.get("newTableStructureVo"), TableStructureVo.class);
        String dbName = map.get("dbName");
        String tbName = map.get("tbName");
        String dataSource = map.get("dataSource");
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                List<TableStructureVo> list = databaseService.modifyColumn(dbName, tbName, oldTableStructureVo, newTableStructureVo);
                return new Response().success(list);
            } catch (Exception e) {
                LOGGER.error("modify table column failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @GetMapping("/show_table_data")
    public Response showTableData(String dbName, String tbName,
                                  int pageNum, int pageSize,
                                  String ordField, boolean sort, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                Map<String, Object> map = databaseService.showTableData(dbName, tbName,
                        pageNum, pageSize, ordField, sort);
                return new Response().success(map);
            } catch (Exception e) {
                LOGGER.error("modify table column failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @GetMapping("/get_database_charset_and_collation")
    public Response getDatabaseCharsetAndCollation(String dbName, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                Map<String, String> map = databaseService.getDatabaseCharsetAndCollation(dbName);
                return new Response().success(map);
            } catch (Exception e) {
                LOGGER.error("get database charset and collation failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @PostMapping("/update_database_charset_and_collation")
    public Response updateDatabaseCharsetAndCollation(@RequestBody Map<String, String> map) {
        String dataSource = map.get("dataSource");
        String dbName = map.get("dbName");
        String newCharset = map.get("newCharset");
        String oldCharset = map.get("oldCharset");
        String newCollation = map.get("newCollation");
        String oldCollation = map.get("oldCollation");
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                databaseService.updateDatabseCharsetAndCollation(dbName, newCharset, oldCharset,
                        newCollation, oldCollation);
                return new Response().success();
            } catch (Exception e) {
                LOGGER.error("update database charset and collation failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @GetMapping("/export_table")
    public Response exportTable(String dbName, String tbName, String fileName, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                File file = databaseService.exportTable(dbName, tbName, fileName);
                return new Response().success(file);
            } catch (Exception e) {
                LOGGER.error("export table failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @PostMapping("/new_table")
    public Response newTable(@RequestBody Map<String, String> map) {
        List<Object> list = JSONObject.parseObject(map.get("columns"), List.class);
        List<TableStructureVo> columns = new ArrayList<>();
        for(Object obj : list) {
            columns.add(JSONObject.parseObject(obj.toString() ,TableStructureVo.class));
        }
        String tbName = map.get("tbName");
        String dbName = map.get("dbName");
        String engine = map.get("engine");
        String charset = map.get("charset");
        String dataSource = map.get("dataSource");
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                databaseService.newTable(dbName, tbName, engine, charset, columns);
                return new Response().success();
            } catch (Exception e) {
                LOGGER.error("create table failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

    @PostMapping("/execute_sql")
    public Response executeSQL(@RequestBody Map<String, String> map) {
        String dataSource = map.get("dataSource");
        String sqlScript = map.get("sqlScript");
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                String[] sqls = SQLUtils.splitSqlScript(sqlScript);
                if(sqls.length < 1) {
                    return new Response().failure("无可执行语句！");
                } else {
                    Map<String, Object> result = databaseService.executeSqls(sqls);
                    return new Response().success(result);
                }
            } catch (Exception e) {
                LOGGER.error("execute SQL failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }
}
