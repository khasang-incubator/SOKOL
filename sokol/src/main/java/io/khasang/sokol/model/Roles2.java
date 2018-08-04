package io.khasang.sokol.model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles2 implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
