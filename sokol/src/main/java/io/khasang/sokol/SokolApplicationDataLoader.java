package io.khasang.sokol;

import io.khasang.sokol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SokolApplicationDataLoader implements ApplicationRunner {

    private RoleService roleService;

    @Autowired
    public SokolApplicationDataLoader(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleService.initDefaults();
    }
}
