package io.khasang.sokol.service.impl;

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void deleteDepartment(int id) {
        SecurityContext context = SecurityContextHolder.getContext();
        Department updated = departmentDao.getById(id);
        updated.setUpdatedDate(new Date());
        updated.setUpdatedBy(context.getAuthentication().getName());
        updated.setDeleted(true);
        departmentDao.update(updated);
    }

    public Department saveOrUpdateDepartment(final Department department) {
        SecurityContext context = SecurityContextHolder.getContext();
        department.setUpdatedBy(context.getAuthentication().getName());
        int id = department.getId();
        if (id == 0) {
            department.setCreatedBy(context.getAuthentication().getName());
            id = departmentDao.save(department);
        } else {
            department.setUpdatedDate(new Date());
            departmentDao.update(department);
        }
        return departmentDao.getById(id);
    }
}


