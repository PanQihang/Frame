package com.oj.controller.statistics;

import com.oj.frameUtil.JqueryDataTableDto;
import org.slf4j.Logger;
import com.oj.entity.statistics.Form;
import org.slf4j.LoggerFactory;
import com.oj.service.statistics.TodayService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import com.oj.service.statistics.SubmitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/todayMn")
public class TodayController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TodayService todayService;

    //返回班级管理页面
    @RequestMapping("/")
    public String index(ModelMap modelMap, HttpServletRequest request)
    {
        return "statistics/today";
    }

    @RequestMapping("/getStatus")
    @ResponseBody
    public Map<String,String> getStatus(HttpServletRequest request)
    {
        Map<String,String> list = null;
        String college_id = request.getSession().getAttribute("user_college_id").toString();
        list = todayService.getStatus(college_id);
        return list;
    }

    @RequestMapping("/getStatusMaplist")
    @ResponseBody
    public List<Map> getStatusMaplist(HttpServletRequest request)
    {
        List<Map> list = null;
        String college_id = request.getSession().getAttribute("user_college_id").toString();
        list = todayService.getStatusMaplist(college_id);
        return list;
    }
    @RequestMapping("/getNoStatusMaplist")
    @ResponseBody
    public List<Map> getNoStatusMaplist(HttpServletRequest request)
    {
        List<Map> list = null;
        String college_id = request.getSession().getAttribute("user_college_id").toString();
        list = todayService.getNoStatusMaplist(college_id);
        return list;
    }
}
