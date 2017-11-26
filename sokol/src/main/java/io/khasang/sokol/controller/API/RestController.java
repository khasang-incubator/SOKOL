package io.khasang.sokol.controller.API;

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.dao.RequestDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.entity.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping(value = "/getJSON")
public class RestController {

    private DepartmentDao departmentDao;
    private RequestDao requestDao;

    public RestController(DepartmentDao departmentDao, RequestDao requestDao) {
        this.departmentDao = departmentDao;
        this.requestDao = requestDao;
    }

    @ResponseBody
    @RequestMapping(value = "/DepartmentAll", method = RequestMethod.GET)
    public List<Department> departments() {
        //List<Department> departmentList = departmentDao.getAll();
        //return departmentList;
        return departmentDao.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/RequestAll", method = RequestMethod.GET)
    public List<Request> requests() {
        //List<Request> requestList = requestDao.getAll();
        //return requestList;
        return requestDao.getAll();
    }


}
