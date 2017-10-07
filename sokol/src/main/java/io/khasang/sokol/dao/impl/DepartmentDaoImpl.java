/*
 * Copyright 2016-2017 Sokol Development Team
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

package io.khasang.sokol.dao.impl;

import io.khasang.sokol.dao.DepartmentDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import java.util.List;


@Repository
public class DepartmentDaoImpl extends GenericDaoImpl<Department, Integer> implements DepartmentDao {
    public DepartmentDaoImpl() {
        super(Department.class);
    }

/*    @Override
    public String departmentName getDepartmentByUser(String userName) {
        return (String) getSession().createCriteria(User.class)
                .add(Restrictions.eq("department", userName));
    }*/




}
