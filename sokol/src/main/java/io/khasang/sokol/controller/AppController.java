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

    /*    @GetMapping("/")
        public String main() {
            return "/main";
        }*/

    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/")
    public String requestList(Model model) {
        List<Request> requestList = requestRepository.findAll();
        model.addAttribute("requestList", requestList);
        model.addAttribute("headerTitle", "Запросы");
        return "requestList";
    }


    @GetMapping("/main")
    public String index() {
        return "/main";
    }

    @GetMapping("/header")
    public String header() {
        return "/header";
    }


    @GetMapping("/home")
    public String home() {
        return "/login2";
    }

    @GetMapping("/layout")
    public String layout() {
        return "/layout";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    /*    @GetMapping("/index")
        public String home() {
            return "/home";
        }*/
    /*


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



        @PostMapping("/login")
        public String loginSubmit() {
            return "main";
        }





        SecurityContext context = SecurityContextHolder.getContext();*/
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}







