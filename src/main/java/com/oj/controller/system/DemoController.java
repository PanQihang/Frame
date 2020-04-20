package com.oj.controller.system;

import com.oj.frameUtil.LogUtil;
import com.oj.service.classes.ClassService;
import com.oj.service.statistics.SubmitService;
import com.oj.service.student.StudentService;
import com.oj.service.system.AuthService;
import com.oj.service.system.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DemoController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService service;
    @Autowired
    private SubmitService submitService;

    @RequestMapping("/form/")
    //返回login.html页面
    public String login(HttpServletRequest request) {
        return "demo";
    }

    @PostMapping("/form/getCollegeSelectInfo")
    //获取学院下拉信息
    @ResponseBody
    public List<Map> getCollegeSelectInfo(){
        List<Map> list = classService.getCollegeSelectInfo();
        return list;
    }

    @PostMapping("/form/getClassList")
    @ResponseBody
    public List<Map> getClassList(@RequestBody Map<String, String> param, HttpServletRequest request){
        String college_id = param.get("college_id");
        return service.getClassList(college_id);
    }

    @PostMapping("/form/submitForm")
    @ResponseBody
    public Map<String, String> submitForm(@RequestBody Map<String, String> param, HttpServletRequest request){
        String college_id = param.get("college_id");
        return submitService.submitForm(param);
    }
}
