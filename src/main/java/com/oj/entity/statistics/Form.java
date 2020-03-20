package com.oj.entity.statistics;

public class Form {
    private String form_id;

    private String student_id;

    private String class_id;

    private Double temperature;

    private Integer is_headache;

    private String create_time;

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getIs_headache() {
        return is_headache;
    }

    public void setIs_headache(Integer is_headache) {
        this.is_headache = is_headache;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Form{" +
                "form_id='" + form_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", class_id='" + class_id + '\'' +
                ", temperature=" + temperature +
                ", is_headache=" + is_headache +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
