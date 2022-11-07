package com.learningspringboot.RESTfulApp.users;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    
    @GetMapping("/filtering")
    public ResponseBean filtering(){
        return new ResponseBean("val1", "val2", "val3");
    }

    @GetMapping("/filteringlist")
    public List<ResponseBean> filteringList(){
        return Arrays.asList(new ResponseBean("val1", "val2", "val3"), new ResponseBean("val11", "val22", "val33"));
    }

}
