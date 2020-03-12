package com.indestructible_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sbaby
 * @Date 2020/03/12 14:22
 * @Version 1.0
 */
@Data
public class DatabaseAndTables {

    private List<DatabaseAndTable> result = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public class DatabaseAndTable{
        private String label;
        private List<Table> children;
    }

    @Data
    @AllArgsConstructor
    public class Table{
        private String label;
    }

    public void addItem(String dbName, String tbName) {
        for(DatabaseAndTable databaseAndTable : result) {
            if(dbName.equals(databaseAndTable.getLabel())) {
                databaseAndTable.getChildren().add(new Table(tbName));
                return;
            }
        }
        List<Table> list = new ArrayList<>();
        list.add(new Table(tbName));
        result.add(new DatabaseAndTable(dbName, list));
    }

}
