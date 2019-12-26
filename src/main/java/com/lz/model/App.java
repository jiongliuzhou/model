package com.lz.model;
import com.lz.model.config.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author DELL
 * @create 2019/12/25
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan("com.lz.model")
@MapperScan("com.lz.model.mapper")
@EnableTransactionManagement
//注册动态多数据源
@Import({DynamicDataSourceRegister.class})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
