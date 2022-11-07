package com.learningspringboot.RESTfulApp.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @JsonIgnoreProperties is specified on a class
@JsonIgnoreProperties("field1")
public class ResponseBean {
    
    String field1;

    @JsonIgnore
    String field2;

    String field3;

    public ResponseBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }
    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }
    public String getField3() {
        return field3;
    }
    public void setField3(String field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "ResponseBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
    }
}
