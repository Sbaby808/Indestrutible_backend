package com.indestructible_backend.controller;

import com.indestructible_backend.DataSourceHelper.ChangeDataSourceHelper;
import com.indestructible_backend.domain.DBInfo;
import com.indestructible_backend.mapper.DatabaseStructureDao;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Sbaby
 * @Date 2020/03/08 13:46
 * @Version 1.0
 */
@RestController
public class TestController {

    @Resource
    DatabaseStructureDao databaseStructureDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(String name) {
        return "Hi, fuck you " + name + " !";
    }

    @RequestMapping(value = "/get_database_list", method = RequestMethod.POST)
    public List<String> databaseList(@RequestBody DBInfo dbInfo) {
        if(ChangeDataSourceHelper.changeDataSource(dbInfo)) {
            return databaseStructureDao.databaseList();
        } else {
            return null;
        }
    }

}
