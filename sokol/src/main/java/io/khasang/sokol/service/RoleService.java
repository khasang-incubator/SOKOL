package io.khasang.sokol.service;

import io.khasang.sokol.model.Role;
import io.khasang.sokol.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    public static final Integer ADMIN_ID = 1;
    public static final Integer USER_ID = 2;
    public static final String ADMIN_NAME = "ROLE_ADMIN";
    public static final String USER_NAME = "ROLE_USER";

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void initDefaultRoles() {
        if (roleRepository.getOne(ADMIN_ID) == null) {
            roleRepository.save(new Role(ADMIN_ID, ADMIN_NAME));
        }
        if (roleRepository.getOne(USER_ID) == null) {
            roleRepository.save(new Role(USER_ID, USER_NAME));
        }
    }
}
