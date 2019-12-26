package com.lz.model.entity.vo;

import lombok.Data;

/**
 * @author DELL
 * @create 2019/12/26
 * @since 1.0.0
 */
@Data
public class FieldInfo {
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String dataType;
}
