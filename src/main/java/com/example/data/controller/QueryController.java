package com.example.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.data.bean.RestResult;
import com.example.data.bean.Table;
import com.example.data.config.MyHikari;
import com.example.data.bean.Config;
import com.example.data.bean.QueryDO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("query")
public class QueryController {
    private static String queryTableSql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.tables where TABLE_SCHEMA='${dbName}'";
    private static String queryColumnSql = "SELECT TABLE_NAME as tableName,COLUMN_NAME as columnName,ORDINAL_POSITION as ordinalPosition,COLUMN_DEFAULT as columnDefault,\n" +
            "IS_NULLABLE as isNullable,COLUMN_TYPE as columnType,COLUMN_KEY columnKey,EXTRA as extra,COLUMN_COMMENT as columnComment\n" +
            "FROM information_schema.`COLUMNS` WHERE TABLE_SCHEMA = '${dbName}' AND TABLE_NAME = '${tableName}' ORDER BY TABLE_NAME,ORDINAL_POSITION;";
    @PostMapping("doQuery")
    public RestResult doQuery(@RequestBody QueryDO queryDO){
        RestResult restResult = new RestResult();
        String sql = queryDO.getSql().toLowerCase();
        try {
            if(sql.contains("select")){
                List<Map<String, Object>> map=  MyHikari.select(queryDO.getUser(),sql);
                restResult.success(map);
            }else{
                Integer i = MyHikari.update(queryDO.getUser(),sql);
                restResult.success("影响了"+i+"条数据",i);
            }
        }catch (Exception e){
            restResult.fail(e.getMessage());
        }
        return restResult;
    }

    @PostMapping("init")
    public RestResult init(@RequestBody Config config){
        RestResult restResult = new RestResult();
        String id = MyHikari.putConfig(config);
        String sql = "select 1";
        try {
            List<Map<String, Object>> map = MyHikari.select(id,sql);
            if(!CollectionUtils.isEmpty(map)&& !StringUtils.isEmpty(map.get(0).get("1"))){
                restResult.success("连接成功",id);
            } else{
                MyHikari.delConfig(id);
                restResult.fail("连接失败");
            }
        }catch (Exception e){
            restResult.fail(e.getMessage());
        }
        return restResult;
    }

    @GetMapping("getAllTable")
    public RestResult<Table> getAllTable(@RequestParam String user){
        RestResult restResult = new RestResult();
        Config config = MyHikari.getConfig(user);
        String sql= queryTableSql;
        sql = sql.replace("${dbName}",config.getDbName());
        try {
            List<Map<String, Object>> map=  MyHikari.select(user,sql);
            List<Table> tableList = JSONObject.parseArray(JSONObject.toJSONString(map),Table.class);
            restResult.success(tableList);
        }catch (Exception e){
            restResult.fail(e.getMessage());
        }
        return restResult;
    }

    @GetMapping("getColumn")
    public RestResult<Table> getColumn(@RequestParam String user,@RequestParam String tableName){
        RestResult restResult = new RestResult();
        Config config = MyHikari.getConfig(user);
        String sql= queryColumnSql;
        sql = sql.replace("${dbName}",config.getDbName()).replace("${tableName}",tableName);
        try {
            List<Map<String, Object>> map=  MyHikari.select(user,sql);
            List<Table> tableList = JSONObject.parseArray(JSONObject.toJSONString(map),Table.class);
            restResult.success(tableList);
        }catch (Exception e){
            restResult.fail(e.getMessage());
        }
        return restResult;
    }
}
