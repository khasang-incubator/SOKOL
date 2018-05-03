/*
 * Copyright 2016-2018 Sokol Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.khasang.sokol.service;

import io.khasang.sokol.model.Role;
import io.khasang.sokol.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    public static final Integer ADMIN_ID = 1;
    public static final String ADMIN_NAME = "ROLE_ADMIN";
    public static final Integer USER_ID = 2;
    public static final String USER_NAME = "ROLE_USER";

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void initDefaults() {
        if (!roleRepository.exists(ADMIN_ID)) {
            roleRepository.save(new Role(ADMIN_ID, ADMIN_NAME));
        }
        if (!roleRepository.exists(USER_ID)) {
            roleRepository.save(new Role(USER_ID, USER_NAME));
        }
    }
}
