/*
 * Copyright 2016-2017 Sokol Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.khasang.sokol.controller;

import io.khasang.sokol.dao.RoleDao;
import io.khasang.sokol.dao.UserDao;
import io.khasang.sokol.entity.Role;
import io.khasang.sokol.entity.User;
import io.khasang.sokol.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class AppController {
    private static final Logger log = Logger.getLogger("CreateTable");

    @Autowired
    CreateTable createTable;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @RequestMapping("/")
    public String hello(Model model) {
//        model.addAttribute("hello", messageService.getInfo());
        // get security context from thread local

        SecurityContext context = SecurityContextHolder.getContext();
        context.getAuthentication().getName();
        if (context == null) {
            return "index";
        }
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return "index";
        }
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            log.info("\r\n==================== ROLE = " + auth.getAuthority());
        }
        if (authentication.getName() != "anonymousUser")
            return "redirect:/mypanel";
        return "index";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(SessionStatus status) {
        status.setComplete();
        return "index";
    }

    @RequestMapping("/admin")
    public String hello2(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(//Model model,
                              @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
//        model.addAttribute("user", user);
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        model.getModel().put("roles", roleDao.getForRegisterForm());
        model.setViewName("register");
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(User usr, String roleId) {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Post registration form" + usr.getLogin());

        Role role = roleDao.getByName("ROLE_USER");
        usr.setLogin(usr.getEmail());
        usr.setRole(role);
        usr.setPassword(new BCryptPasswordEncoder().encode(usr.getPassword()));
        usr.setEnabled(true);
        usr.setCreatedBy(usr.getLogin());
        usr.setUpdatedBy(usr.getLogin());
        userDao.save(usr);
        model.setViewName("registerSuccess");
        return model;
    }

    @RequestMapping("/first")
    public String firstPage(Model model) {
        model.addAttribute("user", new User().getLogin());
        return "first";
    }

    @RequestMapping("/second")
    public String secondPage(Model model, @ModelAttribute User modelUser) {
        model.addAttribute("user", modelUser);
        return "second";
    }

    @RequestMapping("/createtable")
    public String createTable(Model model) {
        model.addAttribute("createTable", createTable.createTable());
        return "createtable";
    }

    @RequestMapping(value = "password/{password}", method = RequestMethod.GET)
    public String password(@PathVariable("password") String password, Model model) {
        model.addAttribute("crypt", new BCryptPasswordEncoder().encode(password));
        return "password";
    }
}
