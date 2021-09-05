package com.example.data.config;

import com.example.data.bean.Config;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

public class MyHikari {

    private static HashMap<String, Config> dbMap = new HashMap<>();

    private static HashMap<String,String> dbIdMap = new HashMap<>();

    public static HikariDataSource customDataSource(String id) {
        Config config = dbMap.get(id);
        final HikariDataSource customDataConfig = new HikariDataSource();

        customDataConfig.setJdbcUrl("jdbc:mysql://"+config.getUrl()+":"+config.getPort()+"/"+config.getDbName());
        customDataConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        customDataConfig.setUsername(config.getUserName());
        customDataConfig.setPassword(config.getPassword());

        customDataConfig.setMaximumPoolSize(2);
        customDataConfig.setMinimumIdle(1);
        customDataConfig.setAutoCommit(true);
        //customDataConfig.setConnectionTestQuery("SELECT 1 FROM DUAL");
        customDataConfig.addDataSourceProperty("cachePrepStmts", "true");
        customDataConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        customDataConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return customDataConfig;
    }

    public static List<Map<String, Object>> select(String id,String sql){
        HikariDataSource dataSource = customDataSource(id);
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        List<Map<String, Object>> mapList = null;
        try {
            mapList = jdbcTemplate.queryForList(sql, new HashMap<>());
            dataSource.close();
        }catch (Exception e){
            throw e;
        }
        return mapList;
    }

    public static Integer update(String id,String sql){
        HikariDataSource dataSource = customDataSource(id);
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Integer i = 0;
        try {
            i = jdbcTemplate.update(sql, new HashMap<>());
            dataSource.close();
        }catch (Exception e){
            throw e;
        }
        return i;
    }

    public static String putConfig(Config config){
        String key = "jdbc:mysql://"+config.getUrl()+":"+config.getPort()+"/"+config.getDbName();
        Random r = new Random();
        String id = dbIdMap.getOrDefault(key,String.valueOf(r.nextInt(100000)));
        dbMap.put(id,config);
        dbIdMap.put(key,id);
        return id;
    }

    public static void delConfig(String id){
        if(dbMap.containsKey(id)){
            Config config = dbMap.get(id);
            dbMap.remove(id);
            String key = "jdbc:mysql://"+config.getUrl()+":"+config.getPort()+"/"+config.getDbName();
            dbIdMap.remove(key);
        }
    }

    public static Config getConfig(String id){
        return dbMap.getOrDefault(id,new Config());
    }
}
