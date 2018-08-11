package io.khasang.sokol.controller;

import io.khasang.sokol.model.Request;
import io.khasang.sokol.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AppController {

    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/")
    public String index() {
        return "/main";
    }

    @GetMapping("/header")
    public String header() {
        return "/fragments/header";
    }

    @GetMapping("/layout")
    public String layout() {
        return "/layout";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/mypanel")
    public String mypanel() {
        return "/mypanel";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}







