package com.indestructible_backend.controller;

import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.domain.Response;
import com.indestructible_backend.service.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Sbaby
 * @Date 2020/03/09 15:05
 * @Version 1.0
 */
@RestController
public class InitController {

    private static Logger LOGGER = LoggerFactory.getLogger(InitController.class);

    @Autowired
    InitService initService;

    @PostMapping("/test_conn")
    public Response testConnection(@RequestBody DBInfo dbInfo) {
        try {
            if(initService.testConnection(dbInfo)) {
                return new Response().success();
            } else {
                return new Response().failure();
            }
        } catch (Exception e) {
            LOGGER.error("test connection failed!", e);
            return new Response().failure(e.getCause().getCause().getMessage());
        }
    }

}
