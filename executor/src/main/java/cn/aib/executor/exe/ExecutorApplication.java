/*
 * ExecutorApplication.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.executor.exe;

import cn.aib.poi.component.ExcelTestComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = { "cn.aib" })
public class ExecutorApplication implements CommandLineRunner {

    @Autowired
    private ExcelTestComponent excelTestComponent;

    public static void main(String[] args) throws Exception {
        final ConfigurableApplicationContext run = SpringApplication.run(ExecutorApplication.class,
                args);
        Thread.sleep(10 * 1000);
        run.close();
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("[ExecutorApplication-run]:" + "test");
        excelTestComponent.test();
    }
}
