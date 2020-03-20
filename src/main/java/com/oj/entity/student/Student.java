package com.oj.entity.student;

public class Student {
    private String student_id;

    private String name;

    private String college_id;

    private String class_id;

    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public Student(String account,  String name, String class_id,String college_id) {
        this.account = account;
        this.name = name;
        this.class_id = class_id;
        this.college_id = college_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id='" + student_id + '\'' +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", class_id='" + class_id + '\'' +
                '}';
    }
}
