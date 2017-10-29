package io.khasang.sokol.controller;

import io.khasang.sokol.service.impl.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    private MyService myService;

    @Autowired
    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping(path = "/message", method = RequestMethod.GET,
            produces = "text/plain")
    public String textMessage() {
        return myService.textMyService();
    }
}
