package io.khasang.sokol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping({"/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/footer")
    public String layuot(Model model) {
        return "footer";
    }

    @GetMapping("/header")
    public String header() {
        return "header";
    }

    @GetMapping("/testdrop")
    public String testdrop() {
        return "testdrop";
    }

    @GetMapping("/layuot")
    public String layuot() {
        return "layuot";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit() {
        return "main";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}







