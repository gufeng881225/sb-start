package com.sb.staging.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -6060343040263809614L;

    @Id
    private Long id;
    private String name;
    private String code;
    private String year;

    public Student() {
    }

    public Student(String userName, String password, String year) {
        this.name = userName;
        this.code = password;
        this.year = year;
    }
}
