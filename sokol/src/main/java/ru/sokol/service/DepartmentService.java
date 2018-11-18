/*
 * Copyright 2016-2018 Sokol Development Team
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
package ru.sokol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sokol.dto.department.CreateDepartmentRequest;
import ru.sokol.dto.department.DepartmentDto;
import ru.sokol.dto.department.UpdateDepartmentRequest;
import ru.sokol.model.Department;
import ru.sokol.repository.DepartmentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mikhail Bedritskiy
 */
@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> findAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public DepartmentDto findDepartmentById(Integer departmentId) {
        Department department = findById(departmentId);
        return convert(department);
    }

    public DepartmentDto createDepartment(CreateDepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        departmentRepository.save(department);
        return convert(department);
    }

    public DepartmentDto updateDepartment(UpdateDepartmentRequest request) {
        Department department = findById(request.getId());
        department.setName(request.getName());
        departmentRepository.save(department);
        return convert(department);
    }

    private Department findById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department was not found by id = " + departmentId));
    }

    private DepartmentDto convert(Department department) {
        DepartmentDto result = new DepartmentDto();
        result.setId(department.getId());
        result.setName(department.getName());
        return result;
    }

    public void deleteDepartmentById(Integer departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}