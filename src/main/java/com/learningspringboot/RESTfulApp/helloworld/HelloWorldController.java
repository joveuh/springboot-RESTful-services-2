package com.learningspringboot.RESTfulApp.helloworld;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.learningspringboot.RESTfulApp.exception.CustomizedResponseEntityExceptionHandler;

@RestController
public class HelloWorldController {

    private MessageSource messageSoruce;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSoruce = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET, path = "helloworld_internationalize")
    public String helloWorldInternationalize() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSoruce.getMessage("good.morning.message", null, "Default message", locale);
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
