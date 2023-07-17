package com.yangcan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class BookManagementApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BookManagementApplication.class, args);
    }

}
