package com.oj.service.serviceImpl.college;

import com.oj.entity.college.College;
import com.oj.mapper.college.CollegeMapper;
import com.oj.service.college.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired(required = false)
    private CollegeMapper mapper;

    /*


    @Transactional
    //班级删除
    public void classDelete(String id)
    {
        mapper.classDelete(id);
    }

    //@Override
    //获取学生列表
    public List<Map> getStudentMapByClassList(String id) {
        return mapper.getStudentMapByClassList(id);
    }*/

    @Override
    public List<Map> getCollegeMapList(Map<String, String> param) {
        return mapper.getCollegeMapList(param);
    }

    @Override
    public void saveOrUpdateCollege(College college) throws Exception {
        //若当前班级号已经存在，则抛出班级已存在的异常
        if(mapper.getClassByName(college.getName()).size()>0){
            throw new Exception("当前学院名称已存在!");
        }else{
            //若班级id为空，为保存
            if (StringUtils.isEmpty(college.getId())){
                mapper.save(college);
            }else{
                mapper.update(college);
            }
        }
    }

    @Override
    public void collegeDelete(String id) {
        mapper.collegeDelete(id);
    }

    @Override
    public List<Map> getClassMap(String id) {
        return null;
    }
}
