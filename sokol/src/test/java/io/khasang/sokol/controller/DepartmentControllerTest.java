package io.khasang.sokol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.khasang.sokol.model.Department;
import io.khasang.sokol.service.DepartmentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc(secure = false)
public class DepartmentControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        Department dept1 = new Department(1L, "DeptA");
        Department dept2 = new Department(2L, "DeptB");
        Department dept3 = new Department(3L, "DeptC");
        List<Department> expectedDepartments = Arrays.asList(dept1, dept2, dept3);

        when(service.findAllByDeletedIsFalse()).thenReturn(expectedDepartments);

//        given(service.getAllEmployees()).willReturn(allEmployees);

//        mockMvc.perform(get("/department/list")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(expectedDepartments.size())));
    }
}