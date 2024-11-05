package com.softka.prueba.ingreso.cuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackages = {"com.softka.common.service.entity","com.softka.prueba.ingreso.cuenta.entity"})
@EnableFeignClients
public class CuentaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentaServiceApplication.class, args);
	}

}
