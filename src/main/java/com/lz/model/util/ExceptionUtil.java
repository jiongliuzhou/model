package com.lz.model.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 获取完整的异常信息
 * @author lz
 * @create 2019/6/14
 * @since 1.0.0
 */
public class ExceptionUtil {
    private ExceptionUtil(){

    }
    /**
     * 处理程序异常返回信息
     * @param e
     * @return
     */
    public static String dealException(Exception e){
        String message;
        try(StringWriter sw = new StringWriter(); PrintWriter pw=new PrintWriter(sw)){
            e.printStackTrace(pw);
            message=sw.toString();
        }catch (Exception e1){
            message=e1.getMessage();
        }
        return message;
    }
}
