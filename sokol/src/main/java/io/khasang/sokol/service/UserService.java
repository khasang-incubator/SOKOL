package io.khasang.sokol.service;

import io.khasang.sokol.model.Roles2;
import io.khasang.sokol.model.User;
import io.khasang.sokol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.Collections;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (!(userRepository.findByUsername("username") == null)) {
            userRepository.save(User.builder()
                    .username("username12")
                    .password("password12")
                    .authorities(Collections.singletonList(Roles2.USER))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build()
            );
        }
    }

    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
