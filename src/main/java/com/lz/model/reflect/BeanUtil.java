package com.lz.model.reflect;

import org.springframework.util.StringUtils;

/**
 * @author DELL
 * @create 2019/12/26
 * @since 1.0.0
 */
public class BeanUtil {
    private BeanUtil(){}


    /**
     * 获取属性get方法名
     * @param name
     * @return
     */
    public static String getMethodName(String name,String separator){
        return "get"+getUpperName(name,separator);
    }

    /**
     * 获取属性set方法名
     * @param name
     * @return
     */
    public static String setMethodName(String name,String separator){
        return "set"+getUpperName(name,separator);
    }

    /**
     * 将表名转化为java命名风格
     * @param name
     * @return
     */
    public static String getUpperName(String name,String separator){
        String fieldName = getFieldName(name,separator);
        String upper= String.valueOf(fieldName.charAt(0)).toUpperCase();
        return upper+fieldName.substring(1);
    }

    /**
     * 将字段转化为java命名风格
     * @param name
     * @return
     */
    public static String getFieldName(String name,String separator){
        name=name.toLowerCase();
        int start=0;
        //处理分隔符在首位
        if(String.valueOf(name.charAt(0)).equals(separator)){
            name=name.substring(1);
        }
        //处理分隔符在末尾
        if(String.valueOf(name.charAt(name.length()-1)).equals(separator)){
            name=name.substring(0,name.length()-1);
        }
        //处理中间分隔符
        StringBuilder temName=new StringBuilder();
        while(start<name.length()){
            int index = name.indexOf(separator,start);
            String upper=null;
            if(index!=-1){
                upper = String.valueOf(name.charAt(index + 1)).toUpperCase();
            }else{
                index=name.length();
            }
            temName.append(name.substring(start,index));
            if(!StringUtils.isEmpty(upper)){
                temName.append(upper);
            }
            start=index+2;
        }
        return temName.toString();
    }
}
