package com.lz.model.mapper;

import com.lz.model.entity.request.DataBaseRequest;
import com.lz.model.entity.vo.FieldInfo;
import com.lz.model.entity.vo.TableInfoVO;
import com.lz.model.mapper.provider.OracleProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import java.util.List;

/**
 * @author DELL
 * @create 2019/12/25
 * @since 1.0.0
 */
public interface OracleMapper{
    /**
     * 获取当前数据库所有表名
     * @param request
     * @return
     */
    @SelectProvider(type = OracleProvider.class,method = "getAllTables")
    @Results({
            @Result(property = "tableName",column = "tb"),
            @Result(property = "fields",javaType=List.class,column = "databaseName=databaseName,tableName=tableName",
                    many = @Many(select = "com.lz.model.mapper.OracleMapper.getTableColumns"))})
    List<TableInfoVO> getAllTables(DataBaseRequest request);

    /**
     * 获取表所有字段
     * @param tableName
     * @return
     */
    @SelectProvider(type = OracleProvider.class,method = "getTableColumns")
    List<FieldInfo> getTableColumns(@Param("databaseName") String databaseName,@Param("tableName") String tableName);


}
