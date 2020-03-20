package com.oj.service.college;

import com.oj.entity.college.College;

import java.util.List;
import java.util.Map;

public interface CollegeService {
    // 获取学院列表
    public List<Map> getCollegeMapList(Map<String, String> param);
    //保存或更新学院
    public void saveOrUpdateCollege(College college) throws Exception;
    //学院删除
    public void collegeDelete(String id);
    //获取班级列表
    public List<Map> getClassMap(String id);
}
