package io.khasang.sokol.controller.API;

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.dao.RequestDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.entity.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping(value = "/api/v1")
public class ApiV1Controller {

    private DepartmentDao departmentDao;
    private RequestDao requestDao;

    public ApiV1Controller(DepartmentDao departmentDao, RequestDao requestDao) {
        this.departmentDao = departmentDao;
        this.requestDao = requestDao;
    }


    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public List<Department> departments() {
        //List<Department> departmentList = departmentDao.getAll();
        //return departmentList;
        return departmentDao.getAll();
    }


    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public List<Request> requests() {
        //List<Request> requestList = requestDao.getAll();
        //return requestList;
        return requestDao.getAll();
    }


}
