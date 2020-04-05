package com.indestructible_backend.domain;

import lombok.Data;

/**
 * @Author Sbaby
 * @Date 2020/04/05 15:30
 * @Version 1.0
 */
@Data
public class TableAttribute {

    private String TABLE_SCHEMA;
    private String TABLE_NAME;
    private String ENGINE;
    private String TABLE_COLLATION;

}
