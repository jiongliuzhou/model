package com.lz.model.mapper.provider;

import com.lz.model.entity.request.DataBaseRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author DELL
 * @create 2019/12/25
 * @since 1.0.0
 */
public class SqlServerProvider implements JdbcProvider{

    /**
     * 获取当前数据库所有表名
     * @param request
     * @return
     */
    @Override
    public String getAllTables(DataBaseRequest request) {
        SQL sql=new SQL();
        sql.SELECT("name tableName");
        sql.SELECT("name tb");
        sql.SELECT("'"+request.getDatabaseName()+"' databaseName");
        sql.FROM("sysobjects");
        sql.WHERE("xtype = 'U'");
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
        sql.SELECT("b.name columnName");
        sql.SELECT("c.name dataType");
        sql.FROM("sysobjects a");
        sql.INNER_JOIN("syscolumns b on a.id=b.id and a.xtype='U'");
        sql.INNER_JOIN("systypes c on b.xtype=c.xusertype");
        sql.WHERE("a.name = #{tableName}");
        return sql.toString();
    }


}
