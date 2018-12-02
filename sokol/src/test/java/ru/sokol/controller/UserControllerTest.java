package ru.sokol.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(DataHelper.class)
public class UserControllerTest {

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
    public void createUser() throws Exception {
        int departmentId = dataHelper.createDepartment("UserControllerTest/createDepartment.json").getId();
        String content = DataHelper.readFileAsString("UserControllerTest/createUser.json")
                .replace("111111", String.valueOf(departmentId));
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
//                .andExpect(jsonPath("$.id"))
                .andDo(print());
    }

    @After
    public void tearDown() {
        dataHelper.deleteAllDepartments();
        dataHelper.deleteAllUsers();
    }
}