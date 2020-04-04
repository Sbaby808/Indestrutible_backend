package com.indestructible_backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author Sbaby
 * @Date 2020/04/04 23:19
 * @Version 1.0
 */
public final class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static File createAndWriteSQLFile(String fileName, StringBuffer content) throws IOException {
        File file = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            file = new File(fileName + "_" + UUID.randomUUID() + ".sql");
            if(!file.exists()) {
                file.createNewFile();
            } else {
                throw new RuntimeException("file exists!");
            }
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content.toString());
            bufferedWriter.flush();
            return file;
        } catch (Exception e) {
            LOGGER.error("create file error!", e);
            throw new RuntimeException(e);
        } finally {
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
            if(fileWriter != null) {
                fileWriter.close();
            }
        }
    }

}
