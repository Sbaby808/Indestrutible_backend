package com.indestructible_backend.service;

import com.indestructible_backend.mapper.DatabaseStructureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author Sbaby
 * @Date 2020/03/08 16:41
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestService {

    @Autowired
    DatabaseStructureDao databaseStructureDao;

}
