package com.indestructible_backend.controller;

import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;
import com.indestructible_backend.domain.Response;
import com.indestructible_backend.domain.TableStructure;
import com.indestructible_backend.mapper.DatabaseDao;
import com.indestructible_backend.utils.DataSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author Sbaby
 * @Date 2020/04/05 0:16
 * @Version 1.0
 */
@RestController
public class DownloadController {

    @Resource
    DatabaseDao databaseDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadController.class);

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) {
        try {
            File file = new File(fileName);
            if(file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while(i != -1) {
                        os.write(buffer, 0 ,i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    LOGGER.error("download file error!", e);
                    e.printStackTrace();
                } finally {
                    if(bis != null) {
                        bis.close();
                    }
                    if(fis != null) {
                        fis.close();
                    }
                }
            } else {
                throw new RuntimeException("file not exists!");
            }
        } catch (Exception e) {
            LOGGER.error("download file error!", e);
            e.printStackTrace();
        }
    }

    @GetMapping("/tables")
    public Response test(String dataSource) {
        if(!DataSourceUtil.checkDataSource(dataSource)) {
            return new Response().failure("error dataSource!");
        } else {
            DataSourceContextHolder.setDataSource(dataSource);
            try {
                databaseDao.useDatabase("test");
                List<TableStructure> list = databaseDao.tableStructures("testTable");
                return new Response().success(list);
            } catch (Exception e) {
                LOGGER.error("export database failed!", e);
                return new Response().failure(e.getCause().getMessage());
            }
        }
    }

}
