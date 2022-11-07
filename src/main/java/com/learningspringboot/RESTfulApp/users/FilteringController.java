package com.learningspringboot.RESTfulApp.users;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
    
    @GetMapping("/static_filtering")
    public FilteringResponseBean staticFiltering(){
        return new FilteringResponseBean("val1", "val2", "val3");
    }

    @GetMapping("/static_filteringlist")
    public List<FilteringResponseBean> staticFilteringList(){
        return Arrays.asList(new FilteringResponseBean("val1", "val2", "val3"), new FilteringResponseBean("val11", "val22", "val33"));
    }

    // Don't forget to add "Filter1" on the FilteringResponseBean class' @JsonFilter annotation
    // If FilteringResponseBean has @JsonFilter("Filter2") from /dynamic_filtering_ and you call /dynamic_filtering then you will see:
    // "Internal Server Error","status":500,"detail":"Failed to write response body","instance":"/dynamic_filtering
    @GetMapping("/dynamic_filtering")
    public MappingJacksonValue Dynamicfiltering(){
        FilteringResponseBean filteringResponseBean = new FilteringResponseBean("val1", "val2", "val3");
        MappingJacksonValue objectToPerformFilteringOn = new MappingJacksonValue(filteringResponseBean);
        SimpleBeanPropertyFilter whatToFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider addTheFilter = new SimpleFilterProvider().addFilter("Filter1", whatToFilter);
        objectToPerformFilteringOn.setFilters(addTheFilter);
        return objectToPerformFilteringOn;
    }

    @GetMapping("/dynamic_filtering_")
    public MappingJacksonValue Dynamicfiltering2(){
        FilteringResponseBean filteringResponseBean = new FilteringResponseBean("val1", "val2", "val3");
        MappingJacksonValue objectToPerformFilteringOn = new MappingJacksonValue(filteringResponseBean);
        SimpleBeanPropertyFilter whatToFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
        FilterProvider addTheFilter = new SimpleFilterProvider().addFilter("Filter2", whatToFilter);
        objectToPerformFilteringOn.setFilters(addTheFilter);
        return objectToPerformFilteringOn;
    }

}
