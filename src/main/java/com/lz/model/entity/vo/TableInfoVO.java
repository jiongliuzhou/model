package com.lz.model.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author DELL
 * @create 2019/12/25
 * @since 1.0.0
 */
@Data
public class TableInfoVO {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 属性列表
     */
    private List<FieldInfo> fields;

}
