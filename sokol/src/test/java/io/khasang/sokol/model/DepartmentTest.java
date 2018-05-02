package io.khasang.sokol.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Test
    public void checkNoArgsConstructor() {
        Department department = new Department();
        assertNotNull(department.getUpdatedDate());
        assertEquals(department.getCreatedDate(), department.getUpdatedDate());
    }
}