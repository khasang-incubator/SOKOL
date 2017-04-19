package io.khasang.sokol.controller;

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.dao.RequestDao;
import io.khasang.sokol.dao.RoleDao;
import io.khasang.sokol.dao.UserDao;
import io.khasang.sokol.entity.User;
import io.khasang.sokol.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.logging.Logger;

@Controller
public class MyProfileController {
    private static final Logger log = Logger.getLogger("MyProfile");
    @Autowired
    CreateTable createTable;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @Autowired
    DepartmentDao departmentDao;
    private static final String MY_PROFILE_VIEW = "myprofile";

    @RequestMapping("/myprofile")
    public String myprofile(Model model) {


        User currentUsr = getCurrentUser();

        model.addAttribute("user", currentUsr);
        fillDictionaries(model);
        model.addAttribute("headerTitle", "Редактирование пользователя");
        return "myprofile";
    }



    private void fillDictionaries(Model model) {
        model.addAttribute("roles", roleDao.getAll());
        model.addAttribute("departments", departmentDao.getAll());
    }

    protected User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        return userDao.getByLogin(userName);
    }

    @RequestMapping(value = "/myprofile", method = RequestMethod.POST)
    public String Save(Model model,  User user
            , @RequestParam(value = "confirmPassword", required = false) String confirmPassword
            , @RequestParam(value = "departmentId", required = false) Integer departmentId) {
        //Достаем текущего пользователя и его роль в системе
        User oldUser = getCurrentUser();
        user.setRole(oldUser.getRole());
        user.setLogin(oldUser.getLogin());
        user.setDepartment(departmentDao.getById(departmentId));

        // Если потрогали парошль, то проверяем что его подтвердили
        if (user.getPassword().compareTo("12345") != 0 || !confirmPassword.isEmpty()) {
            if (user.getPassword().compareTo(confirmPassword) == 0) {
                oldUser.Merge(user, user.getPassword());
            } else {
                oldUser.Merge(user);
                preparePasswordPrepareErrorForm(model, oldUser);
                return MY_PROFILE_VIEW;
            }
        } else { // если пароль не трогали, то сохраняем без изменения пароля
            oldUser.Merge(user);
        }
        oldUser.setUpdatedBy(oldUser.getLogin());
        oldUser.setUpdatedDate(new Date());
        userDao.update(oldUser);
        fillDictionaries(model);
        return MY_PROFILE_VIEW;
    }
    private void preparePasswordPrepareErrorForm(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("errorMessage", "Password was not confirmed");
        fillDictionaries(model);
    }



}
