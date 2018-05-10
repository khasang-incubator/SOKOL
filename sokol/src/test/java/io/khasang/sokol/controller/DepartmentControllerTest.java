package io.khasang.sokol.controller;

import io.khasang.sokol.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc(secure = false)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    @Before
    public void setUp() throws Exception {
//        Department dept1 = new Department(1L, "DeptA");
//        Department dept2 = new Department(2L, "DeptB");
//        Department dept3 = new Department(3L, "DeptC");
//        List<Department> expectedDepartments = Arrays.asList(dept1, dept2, dept3);
    }

    @Test
    public void checkList() throws Exception {
        mockMvc.perform(get("/department/list")).andExpect(status().isOk());
    }

    @Test
    public void checkAdd() throws Exception {
        mockMvc.perform(post("/department/add"))
                .andExpect(MockMvcResultMatchers.redirectedUrl(DepartmentController.REDIRECTION_PATH));
        verify(service).saveDepartment(any());
    }
}