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

import io.khasang.sokol.dao.RequestDao;
import io.khasang.sokol.dao.RequestStatusDao;
import io.khasang.sokol.dao.UserDao;
import io.khasang.sokol.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class RequestDaoImpl extends GenericDaoImpl<Request, Integer> implements RequestDao {
    @Autowired
    UserDao userDao;
    @Autowired
    RequestStatusDao requestStatusDao;

    public RequestDaoImpl() {
        super(Request.class);
    }

    @Override
    public Request getByRequestId(Integer requestId) {
        return getSession().get(Request.class, requestId);
    }

    @Override
    public MyPanelScore getScoreIn(String userName) {
        MyPanelScore score = new MyPanelScore(0L, 0L, 0L);
        Session session = getSession();
        Query query = session.createQuery("Select sum (case when f.status.requestStatusId = 1 THEN 1 ELSE 0 END)," +
                "sum(case when f.status.requestStatusId = 2 THEN 1 ELSE 0 END)," +
                "sum(case when f.status.requestStatusId = 3 THEN 1 ELSE 0 END)" +
                "from Request f WHERE f.assignedTo.login = ?");
        query.setParameter(0, userName);
        List<Object[]> scoreQuery = query.list();
        if (scoreQuery.get(0)[0] != null) {
            score.setCountNew((long) scoreQuery.get(0)[0]);
        }
        if (scoreQuery.get(0)[1] != null) {
            score.setCountInProgress((long) scoreQuery.get(0)[1]);
        }
        if (scoreQuery.get(0)[2] != null) {
            score.setCountClosed((long) scoreQuery.get(0)[2]);
        }
        return score;
    }

    @Override
    public MyPanelScore getScoreOut(String userName) {
        MyPanelScore score = new MyPanelScore(0L, 0L, 0L);
        Session session = getSession();
        Query query = session.createQuery("Select sum (case when f.status.requestStatusId = 1 THEN 1 ELSE 0 END)," +
                "sum(case when f.status.requestStatusId = 2 THEN 1 ELSE 0 END)," +
                "sum(case when f.status.requestStatusId = 3 THEN 1 ELSE 0 END)" +
                "from Request f WHERE f.createdBy = ?");
        query.setParameter(0, userName);
        List<Object[]> scoreQuery = query.list();
        if (scoreQuery.get(0)[0] != null) {
            score.setCountNew((long) scoreQuery.get(0)[0]);
        }
        if (scoreQuery.get(0)[1] != null) {
            score.setCountInProgress((long) scoreQuery.get(0)[1]);
        }
        if (scoreQuery.get(0)[2] != null) {
            score.setCountClosed((long) scoreQuery.get(0)[2]);
        }
        return score;
    }

    @Override
    public List<RequestGraphData> getGraphDataIn(String userName) {
        Session session = getSession();
        Query query = session.createQuery("Select date_trunc('day',f.assignedDate) ," +
                "count(f.requestId) " +
                "from Request f WHERE f.assignedTo.login = ? " +
                " group by date_trunc('day',f.assignedDate) order by 1 ");
        query.setParameter(0, userName);

        List<Object[]> scoreQuery = query.list();
        List<RequestGraphData> res = new ArrayList<RequestGraphData>();

        scoreQuery.forEach(x -> res.add(new RequestGraphData((Date) x[0], ((Long) x[1]).intValue())));
        return res;
    }

    @Override
    public List<RequestGraphData> getGraphDataOut(String userName) {
        Session session = getSession();
        Query query = session.createQuery("Select date_trunc('day',f.createdDate) ," +
                "count(f.requestId) " +
                "from Request f WHERE f.createdBy = ? " +
                " group by date_trunc('day',f.createdDate)  order by 1 ");
        query.setParameter(0, userName);
        List<Object[]> scoreQuery = query.list();
        List<RequestGraphData> res = new ArrayList<RequestGraphData>();
        scoreQuery.forEach(x -> res.add(new RequestGraphData((Date) x[0], ((Long) x[1]).intValue())));
        return res;
    }

    @Override
    public List<Request> getMyRequests(String userName) {
        RequestStatus statusClosed = requestStatusDao.getById(3);
        RequestStatus statusCanceled = requestStatusDao.getById(4);
        return (List<Request>) getSession().createCriteria(Request.class)
                .add(Restrictions.eq("createdBy", userName))
                .add(Restrictions.ne("status", statusClosed))
                .add(Restrictions.ne("status", statusCanceled))
                .list();

    }

    @Override
    public List<Request> getRequestsForMe(String userName) {
        User user = userDao.getByLogin(userName);
        RequestStatus statusClosed = requestStatusDao.getById(3);
        RequestStatus statusCanceled = requestStatusDao.getById(4);
        return (List<Request>) getSession().createCriteria(Request.class)
                .add(Restrictions.eq("assignedTo", user))
                .add(Restrictions.ne("status", statusClosed))
                .add(Restrictions.ne("status", statusCanceled))
                .list();
    }

    @Override
        public List<Request> getRequestFound(String found) {
            Session session = getSession();
            Query query = session.createQuery("from Request f WHERE f.title like ? OR f.description like ?");
            //  Query query = session.createQuery("from Request f WHERE  f.title like '%22%' OR f.description like '%22%'");
            query.setParameter(0, "%"+found+"%");
            query.setParameter(1, "%"+found+"%");

            return query.list();
        // Session session = getSession();
      //  Criteria criteria = getSession().createCriteria(Request.class);
     //   criteria.add(Restrictions.eq("title", found));
      //  return criteria.list();
       // return (List<Request>) getSession().createCriteria(Request.class)
       //         .add(Restrictions.eq("title", found));
               // .uniqueResult();
    }

    @Override
    public Request getByName(String name) {
        return (Request) getSession().createCriteria(Request.class)
                .add(Restrictions.eq("title", name))
                .uniqueResult();
    }

    @Override
    public Integer getCountLineOfTable() {
        Session session = getSession();
        String countQ = "Select count (f.requestId) from Request f";
        Query countQuery = session.createQuery(countQ);
        long countResults = (long) countQuery.uniqueResult();
        return (int) countResults;
    }

    @Override
    public Integer getCountLineOfTable(String findText) {
        Session session = getSession();
        String countQ = "Select count (f.requestId) from Request f WHERE f.title like ? OR f.description like ?";
        Query countQuery = session.createQuery(countQ);
        countQuery.setParameter(0, "%"+findText+"%");
        countQuery.setParameter(1, "%"+findText+"%");
        long countResults = (long) countQuery.uniqueResult();
        return (int) countResults;
    }
}
