package com.devrary.book.springboot3webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class Springboot3WebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot3WebserviceApplication.class, args);
    }

}
