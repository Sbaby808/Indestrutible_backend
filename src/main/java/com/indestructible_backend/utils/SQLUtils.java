package com.indestructible_backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author Sbaby
 * @Date 2020/4/30 16:06
 * @Version 1.0
 */
public final class SQLUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(SQLUtils.class);

    /**
     * 分离SQL脚本
     */
    public static String[] splitSqlScript(String sqlScript) {
        return sqlScript.split(";");
    }

}
