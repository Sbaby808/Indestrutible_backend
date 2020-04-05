package com.indestructible_backend.domain;

import lombok.Data;

/**
 * @Author Sbaby
 * @Date 2020/04/05 15:16
 * @Version 1.0
 */
@Data
public class TableStructure {

    private String Field;
    private String Type;
    private String Null;
    private String Key;
    private String Default;
    private String Extra;

}
