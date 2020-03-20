package com.oj.entity.student;

import java.util.List;

public class BulkAddStudentPackage {
    private String class_id;
    private String college_id;
    private String Num;
    private List<NewStu> data;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String classId) {
        this.class_id = classId;
    }

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public List<NewStu> getData() {
        return data;
    }

    public void setData(List<NewStu> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BulkAddStudentPackage{" +
                "class_id='" + class_id + '\'' +
                ", college_id='" + college_id + '\'' +
                ", Num='" + Num + '\'' +
                ", data=" + data +
                '}';
    }
}
