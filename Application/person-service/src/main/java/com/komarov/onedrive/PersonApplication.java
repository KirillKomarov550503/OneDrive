package com.komarov.onedrive;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.komarov.onedrive")
@EnableJpaRepositories
public class PersonApplication {

}
