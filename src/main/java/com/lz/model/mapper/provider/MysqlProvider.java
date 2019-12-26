package com.lz.model.mapper.provider;

import com.lz.model.entity.request.DataBaseRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author DELL
 * @create 2019/12/25
 * @since 1.0.0
 */
public class MysqlProvider implements JdbcProvider{

    /**
     * 获取当前数据库所有表名
     * @param request
     * @return
     */
    @Override
    public String getAllTables(DataBaseRequest request) {
        SQL sql=new SQL();
        sql.SELECT("table_name tableName");
        sql.SELECT("table_name tb");
        sql.SELECT("'"+request.getDatabaseName()+"' databaseName");
        sql.FROM("information_schema.tables");
        sql.WHERE("table_schema = #{databaseName}");
        return sql.toString();
    }

    /**
     * 获取表所有字段
     * @param databaseName
     * @return
     */
    @Override
    public String getTableColumns(@Param("databaseName") String databaseName, @Param("tableName") String tableName) {
        SQL sql=new SQL();
        sql.SELECT("name columnName");
        sql.SELECT("DATA_TYPE dataType");
        sql.FROM("information_schema.COLUMNS");
        sql.WHERE("table_name = #{tableName}");
        sql.WHERE("table_schema = #{databaseName}");
        return sql.toString();
    }


}
