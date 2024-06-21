package com.tutorial.bootDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/hello")
    public Response<List<String>> hello() {
        List<String> data = Arrays.asList("Hello", "world");
        return Response.newSuccess(data);
    }
}
