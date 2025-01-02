package com.learningspringboot.RESTfulApp.versioning;

public class Test {
    
    public void test(){
        int[] arr = new int[] {0,1,2};
        int[] test = arr;
        int[] test2 = arr.clone();
    
        System.out.println(arr);
    }
}
