package io.khasang.sokol.repositories;

import io.khasang.sokol.model.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
