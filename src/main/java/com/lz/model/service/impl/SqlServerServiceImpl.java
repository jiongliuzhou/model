package com.lz.model.service.impl;

import com.lz.model.entity.request.DataBaseRequest;
import com.lz.model.entity.vo.TableInfoVO;
import com.lz.model.mapper.SqlServerMapper;
import com.lz.model.service.TransJavaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DELL
 * @create 2019/12/26
 * @since 1.0.0
 */
@Service("sqlServerServiceImpl")
public class SqlServerServiceImpl extends TransJavaService{
    @Resource
    private SqlServerMapper sqlServerMapper;

    /**
     * 初始化数据
     */
    @Override
    public List<TableInfoVO> init(DataBaseRequest request){
        return sqlServerMapper.getAllTables(request);
    }
}
