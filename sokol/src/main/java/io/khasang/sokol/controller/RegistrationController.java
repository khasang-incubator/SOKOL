package io.khasang.sokol.controller;

import io.khasang.sokol.model.Roles;
import io.khasang.sokol.model.User;
import io.khasang.sokol.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage(User user, ModelAndView modelAndView) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public String submitUserRegistration(User user, Model model) {
        log.info("Try to register user {}", user);
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            log.error("User {} already exists", user);
            model.addAttribute("error", true);
            model.addAttribute("error_message", "User already exists!");
            model.addAttribute("user", user);
            return "registration";
        } else {
            user.setEnabled(true);
            user.setAuthorities(Collections.singleton(Roles.USER));
            userRepository.save(user);
            model.addAttribute("user", user);
            log.info("Registration of the user {} was successful", user);
            return "redirect:/login";
        }
    }
}
