package com.oj.controller.statistics;

import com.oj.frameUtil.JqueryDataTableDto;
import org.slf4j.Logger;
import com.oj.entity.statistics.Form;
import org.slf4j.LoggerFactory;
import com.oj.service.statistics.SubmitService;
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
@RequestMapping("/submitMn")
public class SubmitController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SubmitService statisticsService;

    //返回班级管理页面
    @RequestMapping("/")
    public String index(ModelMap modelMap, HttpServletRequest request)
    {
        return "statistics/submit";
    }

    //通过调用接口传递的条件，返回对应的主题信息JsonList
    @RequestMapping("/getSubmitStatusMaplist")
    @ResponseBody
    /*public String getSubmitStatusMaplist(Model model,HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String count = request.getParameter("length");
        String student_name = request.getParameter("name");
        String account = request.getParameter("account");
        String college_id = request.getParameter("college_id");
        String class_id = request.getParameter("class_id");
        String is_headache = request.getParameter("is_headache");
        JqueryDataTableDto jqueryDataTableDto = statisticsService.getSubmitStatusMaplist(start,count,student_name,account,college_id,class_id,is_headache);
        return net.sf.json.JSONObject.fromObject(jqueryDataTableDto).toString();
    }*/
    public List<Map> getSubmitStatusMaplist(@RequestBody Map<String, String> param,HttpServletRequest request)
    {
        List<Map> list = null;
        String college_id = request.getSession().getAttribute("user_college_id").toString();
        if(param.get("college_id")==null||param.get("college_id").equals(""))
        {
            if(!college_id.equals("0"))
            {
                param.put("college_id",college_id);
            }
        }
        list = statisticsService.getSubmitStatusMaplist(param);
        return list;
    }
}
