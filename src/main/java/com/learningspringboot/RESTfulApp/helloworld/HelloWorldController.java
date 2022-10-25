package com.learningspringboot.RESTfulApp.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    public HelloWorldController() {
    }

    @RequestMapping(method = RequestMethod.GET, path = "helloworld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "helloworld2")
    public String helloWorld2() {
        return "Hello World again";
    }

    @GetMapping(path = "hello-world")
    @Bean
    public HelloWorldBean helloWorldFromBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "hello-world/{name}")
    @Bean
    // Removing @Value annotation breaks the code and you see error ---> No
    // qualifying bean of type 'java.lang.String' available: expected at least 1
    // bean which qualifies as autowire candidate.
    //
    // You can either remove @Bean or add @Value if you keep @Bean
    @Value("hello-world/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
