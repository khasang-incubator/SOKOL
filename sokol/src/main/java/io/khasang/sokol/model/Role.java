/*
 * Copyright 2016-2017 Sokol Development Team
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

package io.khasang.sokol.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@NamedQuery(name = "Role.findById", query = "select distinct r from Role r where r.id = :id")
@Table(name = "ROLES")
@Getter
@Setter
public class Role extends AbstractBaseEntity {
/*    @OneToMany(mappedBy = "role")
    Set<User> userSet = new HashSet<>();*/
    @Id
    private int id;
    @Column(name = "ROLE_NAME")
    private String name;
    @Column(name = "ROLE_DESC")
    private String description;

    public Role() {
        super();
    }
}
