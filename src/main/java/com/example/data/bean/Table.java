package com.example.data.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Table {
    @ApiModelProperty(value = "表名称")
    private String tableName;
    @ApiModelProperty(value = "表注释")
    private String tableComment;
    @ApiModelProperty(value = "字段名称")
    private String columnName;
    @ApiModelProperty(value = "字段排序顺序")
    private String ordinalPosition;
    @ApiModelProperty(value = "默认值")
    private String columnDefault;
    @ApiModelProperty(value = "是否允许为null")
    private String isNullable;
    @ApiModelProperty(value = "字段数据类型")
    private String columnType;
    @ApiModelProperty(value = "索引类型")
    private String columnKey;
    @ApiModelProperty(value = "默认类型")
    private String extra;
    @ApiModelProperty(value = "字段注释")
    private String columnComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(String ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
