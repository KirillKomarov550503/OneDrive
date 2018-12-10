package com.komarov.onedrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.komarov.onedrive")
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.komarov.onedrive.feign")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
