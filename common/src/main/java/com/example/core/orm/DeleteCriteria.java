package com.example.core.orm;

import lombok.Data;

import java.util.List;

@Data
public class DeleteCriteria {
    private Object id;
    private String tableName;
    private List<Condition> conditionList;

    public DeleteCriteria(String tableName) {
        this.tableName = tableName;
    }
}
