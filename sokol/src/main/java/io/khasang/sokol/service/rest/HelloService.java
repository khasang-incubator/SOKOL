package io.khasang.sokol.service.rest;

import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Controller
@Path("hello/{name}")
public class HelloService {
    @GET
    @Produces("text/plain")
    public String sayHello(@PathParam("name") String name){
        StringBuilder stringBuilder = new StringBuilder("Hello, ");
        stringBuilder.append(name).append("!");
        return stringBuilder.toString();
    }
}
