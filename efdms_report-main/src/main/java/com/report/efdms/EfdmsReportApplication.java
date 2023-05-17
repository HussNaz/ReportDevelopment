package com.report.efdms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
// @EnableJpaRepositories("com.report.efdms.")
@ComponentScan({"com.report.efdms.*"})
@SpringBootApplication
public class EfdmsReportApplication {

    public static void main(String[] args) {

        SpringApplication.run(EfdmsReportApplication.class, args);
        System.out.println("Live now");
    }

}
