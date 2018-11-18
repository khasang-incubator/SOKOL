package ru.sokol.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import ru.sokol.model.Department;
import ru.sokol.repository.DepartmentRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Andrey Burov
 * @author Mikhail Beditskiy
 */
@Setter
@Component
class DataHelper {

    private final DepartmentRepository departmentRepository;
    private MockMvc mockMvc;

    @Autowired
    DataHelper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    static String readFileAsString(String filename) throws IOException {
        return new String(Files.readAllBytes(new File("src/test/resources/" + filename).toPath()));
    }

    long countDepartments() {
        return departmentRepository.count();
    }

    void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }

    Department createDepartment(String jsonFile) {
        try {
            String jsonString = readFileAsString(jsonFile);
            mockMvc.perform(post("/api/departments")
                    .content(jsonString)
                    .contentType(MediaType.APPLICATION_JSON));
        } catch (Exception ex) {
            throw new RuntimeException("Unable to create department", ex);
        }
        return departmentRepository.findFirstByOrderByIdDesc();
    }

}
