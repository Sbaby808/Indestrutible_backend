package com.indestructible_backend.DataSourceHelper;

import com.alibaba.druid.pool.DruidDataSource;
import com.indestructible_backend.domain.DBInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;

/**
 * 切换数据源助手类
 *
 * @Author Sbaby
 * @Date 2020/03/08 16:24
 * @Version 1.0
 */
public final class ChangeDataSourceHelper {

    private static Logger LOGGER = LoggerFactory.getLogger(ChangeDataSourceHelper.class);

    /**
     * 动态创建切换数据源
     * @param dbInfo
     * @return
     */
    public static boolean changeDataSource(DBInfo dbInfo, String dataSource) {
        try {
            // 创建数据源
            DruidDataSource dynamicDataSource = new DruidDataSource();
            dynamicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dynamicDataSource.setUrl("jdbc:mysql://" + dbInfo.getDbIp() + ":" + dbInfo.getDbPort() + "/information_schema" + "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8");
            dynamicDataSource.setUsername(dbInfo.getDbUser());
            dynamicDataSource.setPassword(dbInfo.getDbPasswd());
            dynamicDataSource.setRemoveAbandoned(false);
            dynamicDataSource.setRemoveAbandonedTimeout(600);
            dynamicDataSource.setLogAbandoned(true);
            dynamicDataSource.setBreakAfterAcquireFailure(true);
            dynamicDataSource.setTimeBetweenConnectErrorMillis(60);
            dynamicDataSource.setConnectionErrorRetryAttempts(10);
            dynamicDataSource.setMaxWait(3000);

            String dataSourceName = UUID.randomUUID().toString();
            Map<Object, Object> dataSourceMap = DynamicDataSource.getInstance().getDataSourceMap();
            dataSourceMap.put(dataSourceName, dynamicDataSource);
            DynamicDataSource.getInstance().setTargetDataSources(dataSourceMap);
            // 切换数据源
            DataSourceContextHolder.setDataSource(dataSourceName);
            // 这行代码很重要，如果不加不会立即建立数据库连接，也就无法检测连接是否正确
            dynamicDataSource.getConnection();
        } catch (Exception e) {
            LOGGER.error("change DataSource failure", e);
            throw new RuntimeException(e);
        }
        return true;
    }
}
