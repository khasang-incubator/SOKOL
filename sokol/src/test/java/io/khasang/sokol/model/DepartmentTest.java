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

    @Test
    public void checkAuditionData() throws InterruptedException {
        Department dept1 = new Department();
        Thread.sleep(100);
        Department dept2 = new Department();
        assertNotEquals(dept1.getCreatedDate(), dept2.getCreatedDate());
        assertNotEquals(dept1.getUpdatedDate(), dept2.getUpdatedDate());
        assertNotEquals(dept1, dept2);
        dept2.setCreatedDate(dept1.getCreatedDate());
        dept2.setUpdatedDate(dept1.getUpdatedDate());
        assertEquals(dept1.getCreatedDate(), dept2.getCreatedDate());
        assertEquals(dept1.getUpdatedDate(), dept2.getUpdatedDate());
        assertEquals(dept1, dept2);
    }
}