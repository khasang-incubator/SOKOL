package io.khasang.sokol.service.impl;

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.service.rest.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentServiceImpl implements DepartmentService {
   private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void deleteDepartment(int id) {
        Department updated = departmentDao.getById(id);
        updated.setUpdatedDate(new Date());
        updated.setDeleted(true);
        SecurityContext context = SecurityContextHolder.getContext();
        updated.setUpdatedBy(context.getAuthentication().getName());
        departmentDao.update(updated);
    }
}
