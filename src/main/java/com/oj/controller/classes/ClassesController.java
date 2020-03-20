package com.oj.controller.classes;

import org.slf4j.Logger;
import com.oj.entity.classes.Class;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import com.oj.service.classes.ClassService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panqihang on 2019/4/9 14:40
 */
@Controller
@RequestMapping("/classesMn")
public class ClassesController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassService classService;

    //返回班级管理页面
    @RequestMapping("/")
    public String index(ModelMap modelMap, HttpServletRequest request)
    {
        return "classes/class";
    }

    @PostMapping("/getRole")
    @ResponseBody
    public Map getRole(HttpServletRequest request)
    {
        Map<String,String> list=new HashMap<String, String>();
        String role = (String) request.getSession().getAttribute("user_role");
        if(role.equalsIgnoreCase("32"))
        {
            list.put("role", "1");
        }
        else
        {
            list.put("role", "2");
        }
        return list;
    }

    @PostMapping("/getClassMapList")
    @ResponseBody
    public List<Map> getClassMapList(@RequestBody Map<String, String> param, HttpServletRequest request)
    {
        String college_id = request.getSession().getAttribute("user_college_id").toString();
        if(param.get("college_id")==null||param.get("college_id").equals(""))
        {
            if(!college_id.equals("0"))
            {
                param.put("college_id",college_id);
            }
        }
        List<Map> list = classService.getClassMapList(param);
        return list;
    }

    @PostMapping("/getCollegeSelectInfo")
    @ResponseBody
    //获取学院下拉信息
    public List<Map> getCollegeSelectInfo(){
        List<Map> list = classService.getCollegeSelectInfo();
        return list;
    }

    //保存或更新班级信息
    @PostMapping("/saveOrUpdateClass")
    @ResponseBody
    public Map<String, String> saveOrUpdateClass(@RequestBody Class clas,HttpServletRequest request)
    {
        String college_id = request.getSession().getAttribute("user_college_id").toString();
        if(clas.getCollege_id()==null||clas.getCollege_id()=="")
        {
            clas.setCollege_id(college_id);
        }
        Map<String, String> map = new HashMap<>();
        try {
            classService.saveOrUpdateClass(clas);
            map.put("flag", "1");
            return map;
        } catch (Exception e){
            map.put("flag", "0");
            map.put("message", e.getMessage());
            log.error(e.getMessage());
            return map;
        }
    }

    //删除班级信息
    @PostMapping("/classDelete")
    @ResponseBody
    public Map<String, String> classDelete(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        try {
            classService.classDelete(request.getParameter("id"));
            map.put("flag", "1");
            return map;
        }catch (Exception e){
            map.put("flag", "0");
            log.error(e.getMessage());
            return map;
        }
    }

    //通过班级查找学生列表
    @PostMapping("/getStudentMapByClassList")
    @ResponseBody
    public List<Map> getStudentMapByClassList(HttpServletRequest request)
    {
        return classService.getStudentMapByClassList(request.getParameter("id"));
    }
}
