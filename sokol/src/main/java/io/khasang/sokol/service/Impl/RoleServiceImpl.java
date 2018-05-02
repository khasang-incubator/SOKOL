package io.khasang.sokol.service.Impl;

import io.khasang.sokol.model.Role;
import io.khasang.sokol.repository.RoleRepository;
import io.khasang.sokol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    public static final Integer ADMIN_ID = 1;
    public static final Integer USER_ID = 2;
    public static final String ADMIN_NAME = "ROLE_ADMIN";
    public static final String USER_NAME = "ROLE_USER";

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        initDefaultRoles();
    }

    @Override
    public void initDefaultRoles() {
        if (roleRepository.getOne(ADMIN_ID) == null) {
            roleRepository.save(new Role(ADMIN_ID, ADMIN_NAME));
        }
        if (roleRepository.getOne(USER_ID) == null) {
            roleRepository.save(new Role(USER_ID, USER_NAME));
        }
    }
}
