package com.indestructible_backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;
import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.domain.Response;
import com.indestructible_backend.domain.vo.TableStructureVo;
import com.indestructible_backend.service.DatabaseService;
import com.indestructible_backend.utils.DataSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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

}
