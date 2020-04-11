package com.indestructible_backend;

import com.github.pagehelper.PageHelper;
import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.mapper.DatabaseDao;
import com.indestructible_backend.service.InitService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class IndestructibleBackendApplicationTests {

    @Resource
    DatabaseDao databaseDao;
    @Autowired
    InitService initService;

    @BeforeEach
    void initDataSource() throws Exception {
        int count = 3;
        while(count-- >= 0) {
            try{
                System.out.println(66);
                if(initService.testConnection(new DBInfo(
                        "139.199.6.85",
                        3306,
                        "guest",
                        "DSds1271643683!"), "")) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        throw new RuntimeException("Connection Error!");
    }

    @Test
    void test() {
        databaseDao.useDatabase("test");
        PageHelper.startPage(0, 5);
        List<Map<String, String>> list = databaseDao.showTableData("testTable");
        System.out.println(list.size());
    }

    @Test
    void contextLoads() throws IOException {

    }

}
