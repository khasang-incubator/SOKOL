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
package io.khasang.sokol.model;

//import io.khasang.sokol.validators.PasswordMatcher;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Table(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@PasswordMatcher(payload=Severity.WARNING.class)
public class User extends AbstractBaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 20)
    private String username;

    private String password;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> authorities;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Column(name = "FIO")
    private String fio;

    @Column(name = "LANGUAGE")
    private String language;

    @ManyToOne
    private Department department;



/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 3, max = 20)
    @NotNull
    @Column(name = "USER_NAME")
    private String login;

    @Column(name = "FIO")
    private String fio;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_ACTIVE")
    private Boolean active;

    private boolean enabled;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Department department;

    @Column(name = "LANGUAGE")
    private String language;

    @Version
    private int version;*/
}

