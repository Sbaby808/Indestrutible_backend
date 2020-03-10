package com.indestructible_backend.utils;

import com.indestructible_backend.DataSourceHelper.DynamicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Sbaby
 * @Date 2020/03/11 1:14
 * @Version 1.0
 */
public final class DataSourceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceUtil.class);

    /**
     * 检查dataSource是否存在
     * @param dataSource
     * @return
     */
    public static boolean checkDataSource(String dataSource) {
        return DynamicDataSource.getInstance().getDataSourceMap().containsKey(dataSource);
    }

}
