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

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {
    private static final String REDIRECT_TO_LIST = "redirect:/department/list";
    private static final String LIST_MAP = "/department/list";
    private static final String FORM_VIEW = "departmentForm";
    private static final String LIST_VIEW = "departmentList";

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showAll(final Model model) {
        model.addAttribute("departmentList", departmentDao.getAll());
        model.addAttribute("headerTitle", "Департаменты");
        return LIST_VIEW;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(final Model model, @PathVariable int id) {
        Department department = departmentDao.getById(id);
        model.addAttribute("cancelUrl", LIST_MAP);
        model.addAttribute("department", department);
        model.addAttribute("headerTitle", String.format("Департамент: %s", department.getTitle()));
        return FORM_VIEW;
    }

/*    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, final Department department) {
        Department updated = departmentDao.getById(id);
        updated.setTitle(department.getTitle());
        updated.setUpdatedDate(new Date());
        departmentDao.update(department);
        return REDIRECT_TO_LIST;
    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, final Department department) {
        if (id == 0) {
            departmentDao.save(department);

        } else {
            Department updated = departmentDao.getById(id);
            updated.setTitle(department.getTitle());
            updated.setUpdatedDate(new Date());
            departmentDao.update(department);

        }
        return REDIRECT_TO_LIST;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showNew(Model model) {
        model.addAttribute("department", new Department());

        String departmentNew = "Новый департамент";
        //
        model.addAttribute("headerTitle", departmentNew);
        return FORM_VIEW;
    }


/*    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Department department) {
        departmentDao.save(department);
        return REDIRECT_TO_LIST;
    }*/

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteRequestType(@PathVariable int id) {
        departmentDao.delete(departmentDao.getById(id));
        return REDIRECT_TO_LIST;
    }
}
