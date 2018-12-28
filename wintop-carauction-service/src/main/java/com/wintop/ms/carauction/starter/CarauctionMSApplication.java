package com.wintop.ms.carauction.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 *
 * @author mike
 *
 * @version 0.0.1
 *
 * @date 2018-10-19
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.wintop.ms.carauction.mapper.**")
@ComponentScan("com.wintop.ms.carauction")
@EnableCaching
public class CarauctionMSApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarauctionMSApplication.class, args);
	}
}
