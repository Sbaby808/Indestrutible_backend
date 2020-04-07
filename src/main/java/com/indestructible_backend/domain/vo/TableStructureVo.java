package com.indestructible_backend.domain.vo;

import com.indestructible_backend.domain.TableStructure;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Sbaby
 * @Date 2020/04/06 23:47
 * @Version 1.0
 */
@Data
public class TableStructureVo {

    private String field;
    private String type;
    private int size = 0;
    private boolean notnull;
    private boolean key;

    public TableStructureVo(TableStructure tableStructure) {
        this.field = tableStructure.getField();

        String patternType = "^.+[^(0-9)]";
        String patternSize = "[0-9]+";
        Pattern rType = Pattern.compile(patternType);
        Pattern rSize = Pattern.compile(patternSize);
        Matcher mType = rType.matcher(tableStructure.getType());
        Matcher mSize = rSize.matcher(tableStructure.getType());
        if(mType.find()) {
            this.type = mType.group(0);
        }
        if(mSize.find()) {
            this.size = Integer.parseInt(mSize.group(0));
        }
        this.key = "PRI".equals(tableStructure.getKey());
        this.notnull = "NO".equals(tableStructure.getNull());
    }

    public TableStructureVo(String field, String type, int size, boolean notnull, boolean key) {
        this.field = field;
        this.type = type;
        this.size = size;
        this.notnull = notnull;
        this.key = key;
    }
}
