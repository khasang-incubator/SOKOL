package io.khasang.sokol.controller;

import io.khasang.sokol.model.Department;
import io.khasang.sokol.model.Greeting;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.repository.GreetingRepository;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/sokol")
public class AppController {

    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public void greetingSubmit(@ModelAttribute Greeting greeting) {
        greetingRepository.save(greeting);
        //return "result";
    }

    @GetMapping({"/main"})
    public String main(Model model) {
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

}







