package com.learningspringboot.RESTfulApp.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    
    @GetMapping("/filtering")
    public ResponseBean filtering(){
        return new ResponseBean("val1", "val2", "val3");
    }
}
