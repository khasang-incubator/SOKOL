package io.khasang.sokol;

import io.khasang.sokol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Component
public class SokolApplicationDataLoader implements ApplicationRunner {

    private RoleService roleService;

    @Autowired
    public SokolApplicationDataLoader(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleService.initDefaultRoles();
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
}
