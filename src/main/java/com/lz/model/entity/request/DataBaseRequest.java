package com.lz.model.entity.request;

import lombok.Data;

/**
 * @author DELL
 * @create 2019/12/25
 * @since 1.0.0
 */
@Data
public class DataBaseRequest {
    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 文件输出路径
     */
    private String path;

    /**
     * 数据库字段分隔符
     */
    private String separator;
}
