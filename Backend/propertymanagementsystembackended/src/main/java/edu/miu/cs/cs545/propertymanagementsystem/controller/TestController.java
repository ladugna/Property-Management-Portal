package edu.miu.cs.cs545.propertymanagementsystem.controller;

import edu.miu.cs.cs545.propertymanagementsystem.aop.ExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

    @ExecutionTime
    @GetMapping
    public String test(){
        return "It is working, testing successful";
    }
}
