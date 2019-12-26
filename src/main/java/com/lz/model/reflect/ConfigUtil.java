package com.lz.model.reflect;

import java.util.ResourceBundle;

/**
 * @author DELL
 * @create 2019/12/26
 * @since 1.0.0
 */
public class ConfigUtil {
    private ConfigUtil(){}
    /**
     * 数据名称
     */
    public static final  String DATABASE_TYPE=readConfig("lz.databaseType");
    /**
     * 数据名称
     */
    public static final  String DATABASE_NAME=readConfig("lz.databaseName");
    /**
     * 包名
     */
    public static final  String PACKAGE=readConfig("lz.package");
    /**
     * 文件输出位置
     */
    public static final  String PATH=readConfig("lz.path");
    /**
     * 数据库命名分隔符
     */
    public static final  String SEPARATOR=readConfig("lz.separator");

    private static String readConfig(String key){
        ResourceBundle resource = ResourceBundle.getBundle("config");
        return resource.getString(key).trim();
    }
}
