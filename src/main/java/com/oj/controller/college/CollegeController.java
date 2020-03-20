package com.oj.controller.college;

import com.oj.entity.college.College;
import com.oj.service.college.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/collegeMn")
public class CollegeController {
    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    //依赖注入IpService
    @Autowired
    private CollegeService service;
    //返回首页界面
    @RequestMapping("/")
    public String index(HttpServletRequest request)
    {
        return "college/college";
    }

    @PostMapping("/getCollegeMapList")
    @ResponseBody
    public List<Map> getCollegeMapList(@RequestBody Map<String, String> param, HttpServletRequest request)
    {
        List<Map> list = service.getCollegeMapList(param);
        return list;
    }

    @PostMapping("/saveOrUpdateCollege")
    @ResponseBody
    public Map<String, String> saveOrUpdateClass(@RequestBody College college)
    {
        Map<String, String> map = new HashMap<>();
        try {
            service.saveOrUpdateCollege(college);
            map.put("flag", "1");
            return map;
        } catch (Exception e){
            map.put("flag", "0");
            map.put("message", e.getMessage());
            log.error(e.getMessage());
            return map;
        }
    }
}
