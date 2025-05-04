package edu.epam.fop.spring.boot.dbsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.epam.fop.spring.boot")
public class SpringBootDbSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDbSecurityApplication.class, args);
    }

}
