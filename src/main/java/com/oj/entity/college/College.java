package com.oj.entity.college;

public class College {
    //学院ID
    private String id;
    //学院名称
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "College{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
