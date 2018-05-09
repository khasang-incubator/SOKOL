package io.khasang.sokol.service;

import io.khasang.sokol.model.Role;
import io.khasang.sokol.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RoleServiceTest {

    private RoleRepository roleRepository;
    private RoleService roleService;
    private Role adminRole;
    private Role userRole;

    @Before
    public void setUp() {
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
        adminRole = new Role(RoleService.ADMIN_ID, RoleService.ADMIN_NAME);
        userRole = new Role(RoleService.USER_ID, RoleService.USER_NAME);
    }

    @Test
    public void adminRoleWasCreated() {
        doReturn(false).when(roleRepository).exists(RoleService.ADMIN_ID);
        roleService.initDefaultRoles();
        verify(roleRepository).exists(RoleService.ADMIN_ID);
        verify(roleRepository).save(adminRole);
    }

    @Test
    public void adminRoleAlreadyExists() {
        doReturn(true).when(roleRepository).exists(RoleService.ADMIN_ID);
        roleService.initDefaultRoles();
        verify(roleRepository).exists(RoleService.ADMIN_ID);
        verify(roleRepository, never()).save(adminRole);
    }

    @Test
    public void userRoleWasCreated() {
        doReturn(false).when(roleRepository).exists(RoleService.USER_ID);
        roleService.initDefaultRoles();
        verify(roleRepository).exists(RoleService.USER_ID);
        verify(roleRepository).save(userRole);
    }

    @Test
    public void userRoleAlreadyExists() {
        doReturn(true).when(roleRepository).exists(RoleService.USER_ID);
        roleService.initDefaultRoles();
        verify(roleRepository).exists(RoleService.USER_ID);
        verify(roleRepository, never()).save(userRole);
    }
}