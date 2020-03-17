package com.indestructible_backend.domain;

import lombok.Data;

/**
 * @Author Sbaby
 * @Date 2020/03/18 2:16
 * @Version 1.0
 */
@Data
public class NewDbInfo {

    private String dbName;
    private String charset = " ";
    private String collate = " ";
    private String dataSource;
}
