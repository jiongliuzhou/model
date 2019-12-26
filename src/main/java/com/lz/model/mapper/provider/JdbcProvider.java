package com.lz.model.mapper.provider;

import com.lz.model.entity.request.DataBaseRequest;

/**
 * @author DELL
 * @create 2019/12/25
 * @since 1.0.0
 */
public interface JdbcProvider {

    /**
     * 获取当前数据库所有表名
     * @param request
     * @return
     */
    String getAllTables(DataBaseRequest request);

    /**
     * 获取表所有字段
     * @param tableName
     * @return
     */
    String getTableColumns(String databaseName,String tableName);
}
