package com.lz.model.controller;

import com.lz.model.entity.request.DataBaseRequest;
import com.lz.model.reflect.ConfigUtil;
import com.lz.model.service.TransJavaService;
import com.lz.model.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author DELL
 * @create 2019/12/26
 * @since 1.0.0
 */
@RestController
@Slf4j
public class ModelController {
    @Resource(name="mysqlServiceImpl")
    private TransJavaService mysqlService;
    @Resource(name="oracleServiceImpl")
    private TransJavaService oracleService;
    @Resource(name="sqlServerServiceImpl")
    private TransJavaService sqlServerService;
    /**
     * 生成java文件
     * @return
     */
    @RequestMapping("/doTrans")
    public Object doTrans(DataBaseRequest request){
        //数据库名称
        if(StringUtils.isEmpty(request.getDatabaseType())){
            request.setDatabaseType(ConfigUtil.DATABASE_TYPE);
        }
        String msg="操作失败";
        try{
            if("mysql".equalsIgnoreCase(request.getDatabaseType())){
                mysqlService.doTrans(request);
            }else if("sqlServer".equalsIgnoreCase(request.getDatabaseType())){
                sqlServerService.doTrans(request);
            }else{
                oracleService.doTrans(request);
            }
            msg="操作成功";
        }catch (Exception e){
            ExceptionUtil.dealException(e);
        }
        return msg;
    }
}
