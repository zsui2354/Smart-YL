package org.hae.yl;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.hae.yl", "org.hae.yl.common.config"})
@MapperScan("org.hae.yl.mapper")
public class YlApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlApplication.class, args);
    }
}
