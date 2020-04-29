package com.indestructible_backend.DataSourceHelper;

/**
 * @Author Sbaby
 * @Date 2020/03/11 0:06
 * @Version 1.0
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static synchronized void setDataSource(String dataSource){
        contextHolder.set(dataSource);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }

}
