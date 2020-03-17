package com.indestructible_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Sbaby
 * @Date 2020/03/08 15:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class DBInfo {

    private String dbIp;
    private int dbPort;
    private String dbUser;
    private String dbPasswd;

}
