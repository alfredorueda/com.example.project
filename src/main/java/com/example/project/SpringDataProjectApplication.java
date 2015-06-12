package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringDataProjectApplication {

    public static void main(String[] args) {
	    ConfigurableApplicationContext context = SpringApplication.run(SpringDataProjectApplication.class, args);
    }
}
