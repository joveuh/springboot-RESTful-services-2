package com.learningspringboot.RESTfulApp.helloworld;

import java.util.ArrayList;

public class HelloWorldBean {

    private String message;
    private String tester;
    private ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> getList() {
        // list.add(4);list.add(3);
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloWorldBean(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }

}
