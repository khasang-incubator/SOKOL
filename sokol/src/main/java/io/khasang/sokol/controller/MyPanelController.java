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

package io.khasang.sokol.controller;

import io.khasang.sokol.dao.RequestDao;
import io.khasang.sokol.dao.RoleDao;
import io.khasang.sokol.dao.UserDao;
import io.khasang.sokol.entity.MyPanelScore;
import io.khasang.sokol.entity.Request;
import io.khasang.sokol.entity.RequestGraphData;
import io.khasang.sokol.model.CreateTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MyPanelController {
    private static final Logger log = Logger.getLogger("MyPanel");
    @Autowired
    CreateTable createTable;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    RequestDao requestDao;

    @RequestMapping("/mypanel")
    public String hello(Model model) {
        requestDao.getAll();
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        List<Request> myRequests = requestDao.getMyRequests(userName);
        List<Request> forMeRequests = requestDao.getRequestsForMe(userName);
        MyPanelScore scoreIn = requestDao.getScoreIn(userName);
        MyPanelScore scoreOut = requestDao.getScoreOut(userName);
        List<RequestGraphData> graphDatasIn = requestDao.getGraphDataIn(userName);
        List<RequestGraphData> graphDatasOut = requestDao.getGraphDataOut(userName);

        model.addAttribute("scoreIn", scoreIn);
        model.addAttribute("scoreOut", scoreOut);

        initGraphData(graphDatasIn, model,  "graphDataIn_Date", "graphDataIn_Count");
       initGraphData(graphDatasOut, model,  "graphDataOut_Date", "graphDataOut_Count");

        model.addAttribute("myRequests", myRequests);
        model.addAttribute("forMeRequests", forMeRequests);
        model.addAttribute("headerTitle", "МОЯ ПАНЕЛЬ");
        return "mypanel";
    }


    void initGraphData(List<RequestGraphData> data, Model model, String attrNameDate, String attrNameCount)
    {
        StringBuilder sbDate = new StringBuilder();
        StringBuilder sbCount = new StringBuilder();

        Boolean theFirstLoop = true;

        for (RequestGraphData item :
                data) {
            if(!theFirstLoop){
                sbDate.append(",");
                sbCount.append(",");
            }
            Date requestDate = item.getRequestDate();
            if (requestDate !=null) {
                sbDate.append("\'")
                      .append(new SimpleDateFormat("dd.MM.yyyy").format(requestDate))
                      .append("\'");
                sbCount.append(item.getRequestCount());
                theFirstLoop = false;
            }
/*            if (requestDate !=null) {
                sbDate.append(new StringBuffer().append("\'")
                        .append(new SimpleDateFormat("dd.MM.yyyy").format(requestDate))
                        .append("\'").toString());
                sbCount.append(item.getRequestCount());
                theFirstLoop = false;
            }*/
        }
        model.addAttribute(attrNameDate, sbDate.toString());
        model.addAttribute(attrNameCount, sbCount.toString());
    }
}
