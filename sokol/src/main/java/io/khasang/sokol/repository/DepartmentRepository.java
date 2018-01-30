package io.khasang.sokol.repository;

import io.khasang.sokol.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}