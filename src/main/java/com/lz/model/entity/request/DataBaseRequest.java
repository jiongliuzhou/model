package com.lz.model.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "数据库名称不能为空")
    private String databaseName;

    /**
     * 数据库类型
     */
    @NotBlank(message = "数据库类型不能为空")
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
