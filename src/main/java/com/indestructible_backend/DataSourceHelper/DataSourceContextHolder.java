package com.indestructible_backend.DataSourceHelper;

/**
 * @Author Sbaby
 * @Date 2020/03/08 15:32
 * @Version 1.0
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static synchronized void setDBType(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDBType(){
        return contextHolder.get();
    }

    public static void clearDBType(){
        contextHolder.remove();
    }

}
