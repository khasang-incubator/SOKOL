// spring boot security нет доступа к css
// https://memorynotfound.com/spring-boot-spring-security-thymeleaf-form-login-example/
//https://github.com/drucoder/sweater https://www.youtube.com/watch?v=WDlifgLS8iQ доступ к приложению через проверку в
// пользователей в БД через jdbc
// почему не работает hasRole http://programmerz.ru/questions/17221/spring-security-hasrole-not-working-question

package io.khasang.sokol.config;

//import io.khasang.sokol.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Autowired
    private UserService userService;
    */

/*    @Autowired
    private PasswordEncoder passwordEncoder;*/

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/js/**", "/css/**", "/img/**", "/font-awesome/**", "/", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/mypanel.html", true)
                .failureUrl("/login.html?error=true")
                // .formLogin().defaultSuccessUrl("/", false)
                .loginPage("/login")
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/")
                .permitAll();
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**", "/font-awesome/**").permitAll()
                .antMatchers("/", "/main", "/index", "/home", "/layout", "/about", "/mypanel", "/header", "/img", "/registration").permitAll()
*//*                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER")*//*
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
    }*/

/*    @Autowired
    UserService userService;*/

/*    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    // create two users, admin and user
/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
*//*        auth
                .userDetailsService(userService)
                .passwordEncoder(bcryptPasswordEncoder());*//*
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");

    }*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.authorities from users u inner join user_roles ur on u.id=ur.user_id where username=?");
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }*/


   /* @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);

    }*/


}
