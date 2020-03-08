package com.indestructible_backend.domain;

import lombok.Data;

/**
 * @Author Sbaby
 * @Date 2020/03/08 15:54
 * @Version 1.0
 */
@Data
public class DBInfo {

    private String dbIp;
    private int dbPort;
    private String dbUser;
    private String dbPasswd;

    public DBInfo(String dbIp, int dbPort, String dbUser, String dbPasswd) {
        this.dbIp = dbIp;
        this.dbPort = dbPort;
        this.dbUser = dbUser;
        this.dbPasswd = dbPasswd;
    }
}
