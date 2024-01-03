package com.sb.staging.domain;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = -6060343040263809614L;

    private String name;
    private String code;
    private String year;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Student() {
    }

    public Student(String userName, String password, String year) {
        this.name = userName;
        this.code = password;
        this.year = year;
    }
}
