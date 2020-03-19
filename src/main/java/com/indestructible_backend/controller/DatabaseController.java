package com.indestructible_backend.controller;

import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;
import com.indestructible_backend.domain.NewDbInfo;
import com.indestructible_backend.domain.Response;
import com.indestructible_backend.service.DatabaseService;
import com.indestructible_backend.utils.DataSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
