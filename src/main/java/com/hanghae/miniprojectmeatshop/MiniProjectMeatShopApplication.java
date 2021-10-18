package com.hanghae.miniprojectmeatshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MiniProjectMeatShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProjectMeatShopApplication.class, args);
    }

}
