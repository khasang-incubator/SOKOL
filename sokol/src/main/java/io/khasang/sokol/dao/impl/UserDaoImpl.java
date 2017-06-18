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

import io.khasang.sokol.dao.UserDao;
import io.khasang.sokol.entity.Department;
import io.khasang.sokol.entity.Role;
import io.khasang.sokol.entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getByLogin(String login) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).uniqueResult();
    }

    @Override
    public User getByFio(String fio) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("fio", fio)).uniqueResult();
    }

    @Override
    public List<User> getUserListByDepartment(Department department) {
        return (List<User>) getSession().createCriteria(User.class)
                .add(Restrictions.eq("department", department)).list();
    }

    @Override
    public List<User> getUserListByUser(User user) {
        Role role = user.getRole();

        //Если пользователь администратор, то возвращаем всех
        if (role.getName().contains("ROLE_ADMIN"))
            return this.getAll();

        //Если пользователь менеджер, то возвращаем только пользователь его подразделения
        if (role.getName().contains("ROLE_MANAGER"))
            return this.getUserListByDepartment(user.getDepartment());

        return new ArrayList<User>();
    }

    @Override
    public void delete(User entity) {
        getSession().delete(entity);
    }

    @Override
    public int getCount() {
        Session session = getSession();
        String countQ = "Select count(u.id) from User u";
        Query countQuery = session.createQuery(countQ);
        return (int) countQuery.uniqueResult();
    }
}
