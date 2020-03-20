package com.oj.entity.classes;
/**
 * @author zt
 * @Time 2019年4月2日 17点24分
 * @Description 对应数据库中班级表实体类
 */
public class Class {
    //班级名称
    private String name;
    //学院ID
    private String college_id;
    //班级号ID
    private String class_id;

    public String getName()
    {
        return name;
    }
    public String getCollege_id()
    {
        return college_id;
    }
    public String getClass_id()
    {
        return class_id;
    }

    public void setName(String Class_name)
    {
        this.name = Class_name;
    }
    public void setCollege_id(String major_id)
    {
        this.college_id = major_id;
    }
    public void setClass_id(String class_id)
    {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id='" + class_id + '\'' +
                ", name='" + name + '\'' +
                ", college_id='" + college_id + '\'' +
                '}';
    }
}
