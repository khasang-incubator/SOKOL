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

import io.khasang.sokol.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public class GenericDaoImpl<T, K extends Serializable> implements GenericDao<T, K> {
    private Class<T> type;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public K save(T entity) {
        return (K) getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public T getById(K key) {
        return getSession().get(type, key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Query query = getSession().createQuery(String.format("from %s", type.getName()));
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> sortingBy(Integer firstLine, Integer addLine, String sortBy, String sortOrder) {
        Query query = getSession().createQuery(String.format("FROM %s f ORDER BY f.%s %s ", type.getName(), sortBy, sortOrder));
        query.setFirstResult(firstLine);
        query.setMaxResults(addLine);
        return query.list();
    }

    public List<T> sortingBy(Integer firstLine, Integer addLine, String sortBy, String sortOrder, String findText) {
        Query query = getSession().createQuery(String.format("FROM %s f WHERE f.title like ? OR f.description like ? ORDER BY f.%s %s ", type.getName(), sortBy, sortOrder));
        query.setFirstResult(firstLine);
        query.setMaxResults(addLine);
        query.setParameter(0, "%"+findText+"%");
        query.setParameter(1, "%"+findText+"%");
        return query.list();
    }
}

