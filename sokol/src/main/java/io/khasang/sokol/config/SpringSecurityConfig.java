// spring boot security нет доступа к css
// https://memorynotfound.com/spring-boot-spring-security-thymeleaf-form-login-example/

package io.khasang.sokol.config;

import io.khasang.sokol.model.User;
import io.khasang.sokol.service.RoleService;
import io.khasang.sokol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/js/**", "/css/**", "/img/**", "/font-awesome/**").permitAll()
                    .antMatchers("/", "/main", "/index", "/home", "/layout", "/about", "/mypanel", "/header", "/img").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/mypanel")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }

/*    @Autowired
    UserService userService;*/

/*    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
/*        auth
                .userDetailsService(userService)
                .passwordEncoder(bcryptPasswordEncoder());*/
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");

    }
}
