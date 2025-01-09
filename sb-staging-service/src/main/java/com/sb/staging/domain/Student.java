package com.sb.staging.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = -6060343040263809614L;

    @Id
    private String id;
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

    public static Student ofName(String userName) {
        Student st = new Student();
        st.setName(userName);
        return st;
    }
}
