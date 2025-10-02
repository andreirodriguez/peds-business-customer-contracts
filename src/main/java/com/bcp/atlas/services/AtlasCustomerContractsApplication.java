package com.bcp.atlas.services;

import com.bcp.atlas.core.starter.web.runner.StarterWebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.Executors;

@ComponentScan(lazyInit = true)
@SpringBootApplication
public class AtlasCustomerContractsApplication extends StarterWebApplication {
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int connectionPoolSize;

    public static void main(String[] args) {
        new SpringApplication(AtlasCustomerContractsApplication.class).run(args);
    }

    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

}
