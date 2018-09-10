package org.glenlivet.activiti.sample;

import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://127.0.0.1:3306/activiti?characterEncoding=UTF-8")
                .username("activiti")
                .password("activiti")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }

    @Bean
    public ProcessEngineConfigurationConfigurer processEngineConfigurationConfigurer() {
        return new ProcessEngineConfigurationConfigurerImpl();
    }
}
