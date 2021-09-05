package com.example.data.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryDO {
    @ApiModelProperty(value = "执行的sql语句")
    private String sql;
    @ApiModelProperty(value = "用户ID")
    private String user;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
