package com.komarov.onedrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableJpaRepositories("com.komarov.onedrive.dao.repository")
@SpringBootApplication(scanBasePackages = "com.komarov.onedrive")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
