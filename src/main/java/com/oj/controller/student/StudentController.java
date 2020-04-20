package com.oj.controller.student;


import com.oj.entity.student.Student;
import com.oj.entity.student.BulkAddStudentPackage;
import com.oj.service.student.StudentService;
import com.oj.service.other.ImportService;
import com.oj.service.serviceImpl.other.ImportServicelmpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/studentMn")
public class StudentController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentService service;

    @RequestMapping("/")
    public String index(ModelMap modelMap, HttpServletRequest request) {
        return "student/student";
    }

    //获取指定条件的学生集
    @PostMapping("/getTargetStudentList")
    @ResponseBody
    public List<Map> getTargetStudentList(@RequestBody Map<String, String> param, HttpServletRequest request){
        String college_id = request.getSession().getAttribute("user_college_id").toString();
        if(param.get("college_id")==null||param.get("college_id").equals(""))
        {
            if(!college_id.equals("0"))
            {
                param.put("college_id",college_id);
            }
        }
        List<Map> list = service.getTargetStudentList(param);
        return list;
    }
    //获取所有的班级名称和对应id
    @PostMapping("/getClassList")
    @ResponseBody
    public List<Map> getClassList(@RequestBody Map<String, String> param, HttpServletRequest request){
        String user_college_id = request.getSession().getAttribute("user_college_id").toString();
        String college_id = param.get("college_id");
        if(college_id=="" || college_id==null)
        {
            college_id = user_college_id;
        }
        return service.getClassList(college_id);
    }

    //添加新的学生账号
    @PostMapping("/addNewStudent")
    @ResponseBody
    public Map<String, String> addNewStudent(@RequestBody Map<String, String> param, HttpServletRequest request){
        //why ？这里通过request的getParameter调用无法获取到json数据
        String user_college_id = request.getSession().getAttribute("user_college_id").toString();
        String user_class_id = request.getSession().getAttribute("user_class_id").toString();
        ImportServicelmpl Iservice = new ImportServicelmpl();
        Map<String, String> map = new HashMap<>();
        map.put("elementId", param.get("elementId"));
        try {
            Student student;
            //ImportServicelmpl的checkB调用有对数据格式的规范检查
            String account = param.get("account");
            String name = param.get("name");
            String class_id = param.get("class_id");
            String college_id = param.get("college_id");
            if(college_id==""||college_id==null)
            {
                college_id = user_college_id;
            }
            if(class_id==""||class_id==null)
            {
                class_id = user_class_id;
            }
            student = Iservice.checkB(account, name, class_id,college_id);
            service.addNewStudent(student);
            map.put("result", "succeed");
            return map;
        }catch (Exception e){
            map.put("result", "failed");
            map.put("message", e.getMessage());
            log.error(e.getMessage());
            return map;
        }
    }

    //批量添加学生
    @PostMapping("/bulkAddNewStudent")
    @ResponseBody
    public Map<String, String> bulkAddNewStudent(@RequestBody BulkAddStudentPackage param, HttpServletRequest request){
        String user_college_id = request.getSession().getAttribute("user_college_id").toString();
        String user_class_id = request.getSession().getAttribute("user_class_id").toString();
        ImportServicelmpl Iservice = new ImportServicelmpl();
        Map<String, String> map = new HashMap<>();
        try {
            String class_id = param.getClass_id();
            String college_id = param.getCollege_id();
            if(college_id==""||college_id==null)
            {
                param.setCollege_id(user_college_id);
            }
            if(class_id==""||class_id==null)
            {
                param.setClass_id(user_class_id);
            }
            service.addMoreNewStudent(param);
            map.put("result", "succeed");
            return map;
        }catch (Exception e){
            map.put("result", "failed");
            map.put("message", e.getMessage());
            log.error(e.getMessage());
            return map;
        }
    }

    //通过指定学生编号删除对应学生账号
    @PostMapping("/deleteTheStudent")
    @ResponseBody
    public Map<String, String> deleteTheStudent(@RequestBody Map<String, String> param, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        try {
            service.deleteTheStudentById(param.get("student_id"));
            map.put("result", "succeed");
            return map;
        }catch (Exception e){
            map.put("result", "failed");
            log.error(e.getMessage());
            return map;
        }
    }
    //通过指定学生编号修改学生信息(修改的属性不包括密码)
    @PostMapping("/changeTheStudent")
    @ResponseBody
    public Map<String, String> changeTheStudent(@RequestBody Map<String, String> param, HttpServletRequest request){
        String user_college_id = request.getSession().getAttribute("user_college_id").toString();
        String user_class_id = request.getSession().getAttribute("user_class_id").toString();
        Map<String, String> map = new HashMap<>();
        try {
            String account = param.get("account");
            String name = param.get("name");
            String class_id = param.get("class_id");
            String college_id = param.get("college_id");
            if(college_id==""||college_id==null)
            {
                college_id = user_college_id;
            }
            if(class_id==""||class_id==null)
            {
                class_id = user_class_id;
            }
            Student student = new Student(account, name, class_id,college_id );
            service.updateTheStudentById(param.get("id"), student);
            map.put("result", "succeed");
            return map;
        }catch (Exception e){
            map.put("result", "failed");
            map.put("message", e.getMessage());
            log.error(e.getMessage());
            return map;
        }
    }
    //通过指定学生编号修改其密码
//    @PostMapping("/changeTheStudentPW")
//    @ResponseBody
//    public Map<String, String> changeTheStudentPW(@RequestBody Map<String, String> param, HttpServletRequest request){
//        Map<String, String> map = new HashMap<>();
//        try {
//            service.updateTheStudentPWById(param.get("id"), param.get("password"));
//            map.put("result", "succeed");
//            return map;
//        }catch (Exception e){
//            map.put("result", "failed");
//            log.error(e.getMessage());
//            return map;
//        }
//    }
    //excel文件上传接口(暂废)
    @PostMapping("/upFile")
    @ResponseBody
    public Map<String, String> upFile(HttpServletRequest request, @RequestParam("file") MultipartFile file)  {
        Map<String, String> map = new HashMap<>();
        if(true){ //接口停用，后面的步骤不予处理
            map.put("result","failed");
            return map;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        ImportService importService= new ImportServicelmpl();
        // 测试MultipartFile接口的各个方法
        System.out.println("文件类型ContentType=" + file.getContentType());
        System.out.println("文件组件名称Name=" + file.getName());
        System.out.println("文件原名称OriginalFileName=" + file.getOriginalFilename());
        System.out.println("文件大小Size=" + file.getSize()/1024 + "KB");


        // MultipartFile file = multipartRequest.getFile("filename");
        if (file.isEmpty()) {
            map.put("result", "failed");
            map.put("message", "文件不能为空");
            return map;
        }
        try {
            InputStream inputStream = file.getInputStream();
            if(null==inputStream)
                System.out.println("#inputStream is null");
            //List<List<Object>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
        }catch (Exception e){
            map.put("reuslt", "failed");
            map.put("message", e.getMessage());
            log.error(e.getMessage());
            return map;
        }
        map.put("result", "succeed");
        return map;
    }
}
