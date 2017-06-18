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

import io.khasang.sokol.Exception.ResourceNotFoundException;
import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.dao.RoleDao;
import io.khasang.sokol.dao.UserDao;
import io.khasang.sokol.entity.User;
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
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final String REDIRECT_TO_LIST = "redirect:/users/list";
    private static final String USER_LIST_VIEW = "userList";
    private static final String USER_EDIT_VIEW = "userEdit";

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String List(Model model) {
        //Достаем информацию о текущем пользователе
        User user = getCurrentUser();

        //Получаем списов пользователей, которые может
        //просматривать текущйи пользователь в соответствии со своей ролью
        List<User> users = userDao.getUserListByUser(user);
        model.addAttribute("userList", users);
        model.addAttribute("headerTitle", "Список пользователей");

        return USER_LIST_VIEW;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String Edit(@PathVariable int userId, Model model) throws ResourceNotFoundException {
        //Достаем пользователя, которого хотим редактировать
        User user = getUserById(userId);

        //Если все в порядке, то достаем все необходимые данные и рисуем форму
        model.addAttribute("user", user);
        fillDictionaries(model);
        model.addAttribute("headerTitle", "Редактирование пользователя");
        return USER_EDIT_VIEW;
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

    protected User getUserById(@PathVariable int userId) throws ResourceNotFoundException {
        // Достает пользователя по userId
        User editUser = userDao.getById(userId);

        //Если такого пользователя не нашли то возвращаем 404
        if (editUser == null)
            throw new ResourceNotFoundException();

        //Достаем текущего пользователя и его роль в системе
        User currentUser = getCurrentUser();

        //Проверяем а может ли текущий пользователь
        //радактировать запрашиваемого пользовател
        //Если у пользоватея нет прав на редактироване возвращаем 404
        if (!(currentUser.getRole().getName().contains("ROLE_ADMIN") ||
                (currentUser.getRole().getName().contains("ROLE_MANAGER")
                        && currentUser.getDepartment() != null
                        && currentUser.getDepartment().getId() == editUser.getDepartment().getId())))
            throw new ResourceNotFoundException();
        return editUser;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public String Save(Model model, @PathVariable int userId, User user
            , @RequestParam(value = "confirmPassword", required = false) String confirmPassword
            , @RequestParam(value = "roleId", required = false) Integer roleId
            , @RequestParam(value = "departmentId", required = false) Integer departmentId) {
        //Достаем текущего пользователя и его роль в системе
        User currentUser = getCurrentUser();
        user.setRole(roleDao.getById(roleId));
        user.setDepartment(departmentDao.getById(departmentId));
        //Если userId = 0 то создаем нового польователя
        if (userId == 0) {
            if (user.getPassword().compareTo(confirmPassword) == 0) {
                user.setCreatedBy(currentUser.getLogin());
                user.setUpdatedBy(currentUser.getLogin());
                if (user.getPassword().compareTo(confirmPassword) == 0 && !confirmPassword.isEmpty()) {
                    user.Merge(user, user.getPassword());
                } else {
                    user.Merge(user);
                    preparePasswordPrepareErrorForm(model, user);
                }
                userDao.save(user);
            } else {
                preparePasswordPrepareErrorForm(model, user);
                return USER_EDIT_VIEW;
            }
        } else {
            //Достаем пользователя, которого хотим сохранить
            User oldUser = getUserById(userId);
            // Если потрогали парошль, то проверяем что его подтвердили
            if (user.getPassword().compareTo("12345") != 0 || !confirmPassword.isEmpty()) {
                if (user.getPassword().compareTo(confirmPassword) == 0) {
                    oldUser.Merge(user, user.getPassword());
                } else {
                    oldUser.Merge(user);
                    preparePasswordPrepareErrorForm(model, oldUser);
                    return USER_EDIT_VIEW;
                }
            } else { // если пароль не трогали, то сохраняем без изменения пароля
                oldUser.Merge(user);
            }
            oldUser.setUpdatedBy(currentUser.getLogin());
            oldUser.setUpdatedDate(new Date());
            userDao.update(oldUser);

        }

        return REDIRECT_TO_LIST;
    }

    private void preparePasswordPrepareErrorForm(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("errorMessage", "Password was not confirmed");
        fillDictionaries(model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("user", new User());
        fillDictionaries(model);
        model.addAttribute("headerTitle", "Новый пользователь");
        return USER_EDIT_VIEW;
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
    public String dreateUser(@PathVariable int userId, Model model) {
        //Достаем пользователя, которого хотим сохранить
        User user = getUserById(userId);

        //Удаляем пользователя
        userDao.delete(user);
        model.addAttribute("userEdit", userDao.getAll());
        return USER_EDIT_VIEW;
    }
}
