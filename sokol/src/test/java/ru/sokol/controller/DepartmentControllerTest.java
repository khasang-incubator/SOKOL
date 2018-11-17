package ru.sokol.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.sokol.model.Department;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(DataHelper.class)
@EnableAutoConfiguration
public class DepartmentControllerTest {

    @Autowired
    private DataHelper dataHelper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        dataHelper.setMockMvc(mockMvc);
    }

    @Test
    public void getDepartments() throws Exception {
        assertEquals(0, dataHelper.countDepartments());
        List<Department> departments = Arrays.asList(
                dataHelper.createDepartment("DepartmentControllerTest/createDepartment.json"),
                dataHelper.createDepartment("DepartmentControllerTest/createDepartment2.json"));
        assertEquals(2, dataHelper.countDepartments());
        mockMvc.perform(get("/api/departments/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(departments.get(0).getId())))
                .andExpect(jsonPath("$[0].name", equalTo(departments.get(0).getName())))
                .andExpect(jsonPath("$[1].id", equalTo(departments.get(1).getId())))
                .andExpect(jsonPath("$[1].name", equalTo(departments.get(1).getName())));
    }

    @Test
    public void createDepartment() throws Exception {
        assertEquals(0, dataHelper.countDepartments());
        String content = DataHelper.readFileAsString("DepartmentControllerTest/createDepartment.json");
        mockMvc.perform(post("/api/departments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", any(Integer.class)))
                .andExpect(jsonPath("$.name", equalTo("TEST DEPARTMENT")));
        mockMvc.perform(get("/api/departments/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", any(Integer.class)))
                .andExpect(jsonPath("$[0].name", equalTo("TEST DEPARTMENT")));
        assertEquals(1, dataHelper.countDepartments());
    }

    @After
    public void tearDown() {
        dataHelper.deleteAllDepartments();
    }
}