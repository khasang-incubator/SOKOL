package io.khasang.sokol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
}







