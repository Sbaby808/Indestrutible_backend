package com.indestructible_backend.controller;

import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;
import com.indestructible_backend.domain.Response;
import com.indestructible_backend.service.DatabaseService;
import com.indestructible_backend.utils.DataSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Response newDatabase(String dbName, String charset, String collate, String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                int result = databaseService.newDatabase(dbName, charset, collate, dataSource);
                return new Response().success().success(result);
            } catch (Exception e) {
                LOGGER.error("create database failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

}
