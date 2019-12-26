package com.lz.model.service.impl;

import com.lz.model.entity.request.DataBaseRequest;
import com.lz.model.service.TransJavaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author DELL
 * @create 2019/12/26
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlServerServiceImplTest {
    @Resource(name="sqlServerServiceImpl")
    private TransJavaService transJavaService;

    @Test
    public void tans() throws IOException{
        DataBaseRequest request=new DataBaseRequest();
        transJavaService.doTrans(request);
    }
}
