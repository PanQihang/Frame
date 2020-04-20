package com.oj.service.serviceImpl.statistics;
import com.oj.entity.statistics.Form;
import com.oj.frameUtil.JqueryDataTableDto;
import com.oj.mapper.statistics.SubmitMapper;
import com.oj.service.statistics.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SubmitServicelmpl implements SubmitService{
    @Autowired(required = false)
    private SubmitMapper mapper;

    @Override
    /*public JqueryDataTableDto getSubmitStatusMaplist(String start, String count,String student_name, String account, String college_id, String class_id, String is_headache)  {
        Map<String, String> params = new HashMap<>();
        params.put("start", start);
        params.put("count", count);
        params.put("student_name", student_name);
        params.put("account", account);
        params.put("college_id", college_id);
        params.put("class_id", class_id);
        params.put("is_headache", is_headache);
        JqueryDataTableDto jqueryDataTableDto=new JqueryDataTableDto();
        List<Form> list = mapper.getSubmitStatusMaplist(params);
        int total = mapper.selectTotalCount();
        int filterTotal = mapper.selectRecordsFiltered(params);
        jqueryDataTableDto.setRecordsTotal(total);
        jqueryDataTableDto.setRecordsFiltered(filterTotal);
        jqueryDataTableDto.setData(list);
        return jqueryDataTableDto;
    }*/


    public List<Map> getSubmitStatusMaplist(Map<String, String> param)  {
        //JqueryDataTableDto jqueryDataTableDto=new JqueryDataTableDto();
        List<Map> list = mapper.getSubmitStatusMaplist(param);
        //int total = mapper.selectTotalCount();
        //int filterTotal = mapper.selectRecordsFiltered(params);
        //jqueryDataTableDto.setRecordsTotal(total);
        //jqueryDataTableDto.setRecordsFiltered(filterTotal);
        //jqueryDataTableDto.setData(list);
        return list;
    }

    @Override
    public Map<String, String> submitForm(Map<String, String> param){
        int count = mapper.is_student(param.get("college_id"),param.get("student_account"),param.get("student_name"));
        Map<String, String> map = new HashMap<>();
        if(count==0)
        {
            map.put("result", "error");
            map.put("message", "学生信息输入有误");
        }
        else
        {
            Calendar calendar= Calendar.getInstance();
            String Student_id = mapper.selectStudentIdAccount(param.get("college_id"),param.get("student_account"),
                    param.get("student_name"));
            String create_time = String.valueOf(System.currentTimeMillis());
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
            String form_date = dateFormat.format(calendar.getTime());
            String student_form_date = mapper.selectFormDate(Student_id);
            if(student_form_date.equals(form_date))
            {
                map.put("result", "error");
                map.put("message", "今日已提交，请勿重复提交");
            }
            else
            {
                mapper.insertForm(Student_id,param.get("class_id"),param.get("college_id"),param.get("is_headache"),
                        param.get("temperature"),create_time);
                mapper.updateStudent(form_date,Student_id);
                map.put("result", "success");
                map.put("message", "提交成功");
            }
        }
        return map;
    }

}
