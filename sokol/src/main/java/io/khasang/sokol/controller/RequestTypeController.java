package io.khasang.sokol.controller;

import io.khasang.sokol.dao.RequestTypeDao;
import io.khasang.sokol.entity.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class RequestTypeController {
    private final Logger logger = LoggerFactory.getLogger(RequestTypeController.class);

    @Autowired
    RequestTypeDao requestTypeDao;

    @RequestMapping(value = "/request/types", method = RequestMethod.GET)
    public String showAllRequestTypes(Model model) {
        model.addAttribute("listRequestTypes", requestTypeDao.getAll());
        return "RequestTypeList";
    }

    @RequestMapping(value = "/request/type/{id}", method = RequestMethod.GET)
    public String showRequestType(@PathVariable("id") int id, Model model) {
        model.addAttribute("requestType", requestTypeDao.getById(id));
        return "RequestTypeEdit";
    }

    @RequestMapping(value = "/request/type/add", method = RequestMethod.GET)
    public String addRequestType(Model model) {
        model.addAttribute("RequestTypeAdd", model);
        return "RequestTypeAdd";
    }

    @RequestMapping(value = "/request/type/add", method = RequestMethod.POST)
    public ModelAndView addRequestType(
            @RequestParam("name") String name,
            @RequestParam("description") String description) {
        ModelAndView model = new ModelAndView();
        RequestType requestType = new RequestType();
        requestType.setTitle(name);
        requestType.setDescription(description);
        requestType.setCreatedDate(new Date());
        requestTypeDao.save(requestType);
        model.setViewName("RequestTypeAdd");
        return model;
    }

    @RequestMapping(value = "/editRequestType", method = RequestMethod.GET)
    public String editRequestType(Model model, @RequestParam("id") String requestTypeId) {
        RequestType requestType = requestTypeDao.getById(Integer.parseInt(requestTypeId));
        model.addAttribute("requestType", requestType);
        return "RequestTypeEdit";
    }

    @RequestMapping(value = "/editRequestType", method = RequestMethod.POST)
    public ModelAndView editRequestType(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("description") String description) {
        ModelAndView model = new ModelAndView();
        //RequestType requestType = requestTypeDao.getById(Integer.parseInt(id));
        RequestType requestType = new RequestType();
        requestType.setId(Integer.parseInt(id));
        requestType.setTitle(name);
        requestType.setDescription(description);
        requestType.setUpdatedDate(new Date());
        requestTypeDao.update(requestType);
        model.setViewName("RequestTypeEdit");
        return model;
    }
}
