package com.indestructible_backend.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.indestructible_backend.DataSourceHelper.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sbaby
 * @Date 2020/03/08 15:33
 * @Version 1.0
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.default.url}")
    private String defaultDBUrl;
    @Value("${spring.datasource.default.username}")
    private String defaultDBUser;
    @Value("${spring.datasource.default.password}")
    private String defaultDBPassword;
    @Value("${spring.datasource.default.driver-class-name}")
    private String defaultDBDriverName;
    @Value("${spring.datasource.default.remove-abandoned}")
    private boolean defaultRemoveAbandoned;
    @Value("${spring.datasource.default.remove-abandoned-timeout}")
    private int defaultRemoveAbandonedTimeout;
    @Value("${spring.datasource.default.log-abandoned}")
    private boolean defaultLogAbandoned;
    @Value("${spring.datasource.default.break-after-acquire-failure}")
    private boolean defaultBreakAfterAcquireFailure;
    @Value("${spring.datasource.default.time-between-connect-error-millis}")
    private long defaultTimeBetweenConnectErrorMillis;
    @Value("${spring.datasource.default.connection-error-retry-attempts}")
    private int defaultConnectionErrorRetryAttempts;
    @Value("${spring.datasource.default.max-wait}")
    private int defaultMaxWait;

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();

        DruidDataSource defaultDataSource = new DruidDataSource();
        defaultDataSource.setUrl(defaultDBUrl);
        defaultDataSource.setUsername(defaultDBUser);
        defaultDataSource.setPassword(defaultDBPassword);
        defaultDataSource.setDriverClassName(defaultDBDriverName);
        defaultDataSource.setRemoveAbandoned(defaultRemoveAbandoned);
        defaultDataSource.setRemoveAbandonedTimeout(defaultRemoveAbandonedTimeout);
        defaultDataSource.setLogAbandoned(defaultLogAbandoned);
        defaultDataSource.setBreakAfterAcquireFailure(defaultBreakAfterAcquireFailure);
        defaultDataSource.setTimeBetweenConnectErrorMillis(defaultTimeBetweenConnectErrorMillis);
        defaultDataSource.setConnectionErrorRetryAttempts(defaultConnectionErrorRetryAttempts);
        defaultDataSource.setMaxWait(defaultTimeBetweenConnectErrorMillis);

        Map<Object,Object> map = new HashMap<>();
        map.put("default", defaultDataSource);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);

        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        return bean.getObject();

    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
                return new SqlSessionTemplate(sqlSessionFactory);
            }

}
