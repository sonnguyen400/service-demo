package com.sonnguyen.iamservice2;

import com.sonnguyen.common.vm.KeycloakProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.sonnguyen.iamservice2.*","com.sonnguyen.common.*"})
@EnableWebMvc
@EnableConfigurationProperties(KeycloakProperties.class)
public class IamService2Application {
    public static void main(String[] args) {
        SpringApplication.run(IamService2Application.class, args);
    }
}
