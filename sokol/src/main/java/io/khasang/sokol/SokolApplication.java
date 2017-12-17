package io.khasang.sokol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "io.khasang.sokol")
public class SokolApplication {
    public static void main(String[] args) {
        SpringApplication.run(SokolApplication.class, args);
    }
}
