package com.indestructible_backend.controller;

import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;
import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.domain.DatabaseAndTables;
import com.indestructible_backend.domain.Response;
import com.indestructible_backend.service.InitService;
import com.indestructible_backend.utils.DataSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Sbaby
 * @Date 2020/03/09 15:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/init")
public class InitController {

    private static Logger LOGGER = LoggerFactory.getLogger(InitController.class);

    @Autowired
    InitService initService;

    @PostMapping("/test_conn")
    public Response testConnection(@RequestBody DBInfo dbInfo, String dataSource) {
        try {
            if(initService.testConnection(dbInfo, dataSource)) {
                return new Response().success();
            } else {
                return new Response().failure();
            }
        } catch (Exception e) {
            LOGGER.error("test connection failed!", e);
            return new Response().failure(e.getCause().getCause().getMessage());
        }
    }

    @GetMapping("/get_databases_and_tables")
    public Response getDatabaseAndTables(String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                DatabaseAndTables result = initService.getDatabaseAndTables();
                return new Response().success(result);
            } catch (Exception e) {
                LOGGER.error("get databases and tables failed!", e);
                return new Response().failure(e.getMessage());
            }
        }
    }

}
