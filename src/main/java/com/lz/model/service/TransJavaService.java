package com.lz.model.service;

import com.lz.model.entity.request.DataBaseRequest;
import com.lz.model.entity.vo.FieldInfo;
import com.lz.model.entity.vo.TableInfoVO;
import com.lz.model.reflect.BeanUtil;
import com.lz.model.reflect.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author DELL
 * @create 2019/12/26
 * @since 1.0.0
 */
@Component
@Slf4j
public abstract class TransJavaService {
    /**
     * 初始化
     */
    public abstract List<TableInfoVO> init(DataBaseRequest request);

    /**
     * 转化java代码
     */
    public void doTrans(DataBaseRequest request) throws IOException{
        //初始化参数
        initParam(request);
        //初始化数据
        List<TableInfoVO> tableList = init(request);
        for(TableInfoVO t:tableList){
            //转化java代码
            String trans = trans(request,t);
            //文件名称
            String tableName = t.getTableName();
            String className = BeanUtil.getUpperName(tableName,request.getSeparator());
            //生成java文件
            createFile(className,request.getPath(),trans);
        }
    }

    /**
     * 初始化参数
     * @param request
     */
    private void initParam(DataBaseRequest request){
        //数据库名称
        if(StringUtils.isEmpty(request.getDatabaseName())){
            request.setDatabaseName(ConfigUtil.DATABASE_NAME);
        }
        //包名
        if(StringUtils.isEmpty(request.getPackageName())){
            request.setPackageName(ConfigUtil.PACKAGE);
        }
        //java文件输出路径
        if(StringUtils.isEmpty(request.getPath())){
            request.setPath(ConfigUtil.PATH);
        }
        //数据库字段分隔符
        if(StringUtils.isEmpty(request.getSeparator())){
            request.setSeparator(ConfigUtil.SEPARATOR);
        }
        //数据库名称转化为大小
        request.setDatabaseName(request.getDatabaseName().toUpperCase());
    }

    /**
     * 将一个表转化为类
     * @param tableInfo
     * @return
     */
    private String trans(DataBaseRequest request,TableInfoVO tableInfo){
        StringBuilder source=new StringBuilder();
        //拼接包名
        source.append("\npackage ").append(request.getPackageName()).append(";\n\n");
        //拼接类名
        String tableName = tableInfo.getTableName();
        String className = BeanUtil.getUpperName(tableName,request.getSeparator());
        source.append("public class ").append(className).append(" {\n");
        List<FieldInfo> fields = tableInfo.getFields();
        StringBuilder fieldStr=new StringBuilder();
        StringBuilder methodStr=new StringBuilder();
        for(FieldInfo fieldInfo:fields){
            //拼接属性
            String fieldName = BeanUtil.getFieldName(fieldInfo.getColumnName(),request.getSeparator());
            fieldStr.append("\t").append("private String ").append(fieldName).append(";\n");
            String getMethod = BeanUtil.getMethodName(fieldInfo.getColumnName(),request.getSeparator());
            //拼接get方法
            methodStr.append("\t").append("public String ").append(getMethod).append("(){\n")
                    .append("\t\t").append("return ").append(fieldName).append(";\n\t}\n");
            //拼接set方法
            String setMethod = BeanUtil.setMethodName(fieldInfo.getColumnName(),request.getSeparator());
            methodStr.append("\t").append("public void ").append(setMethod).append("(String ").append(fieldName).append("){\n")
                    .append("\t\t").append("this.").append(fieldName).append("=").append(fieldName).append(";\n\t}\n");
        }
        source.append(fieldStr);
        source.append("\n");
        source.append(methodStr);
        source.append("}");
        return source.toString();
    }

    /**
     * 将生成的java字符串输出到文件
     * @param className 文件名称
     * @param path 输出路径
     * @param javaStr
     */
    private void createFile(String className,String path,String javaStr) throws IOException{
        File filePath=new File(path);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        String p=path+className+".java";
        try (OutputStream out=new FileOutputStream(p)){
            out.write(javaStr.getBytes("UTF-8"));
            out.flush();
        }
    }


}
