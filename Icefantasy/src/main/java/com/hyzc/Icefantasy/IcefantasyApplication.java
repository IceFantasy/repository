package com.hyzc.Icefantasy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hyzc.Icefantasy.mapper") // 扫描 mapper 
@SpringBootApplication
public class IcefantasyApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(IcefantasyApplication.class, args);
	}

}
