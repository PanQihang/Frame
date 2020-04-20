package com.oj.service.serviceImpl.statistics;
import com.oj.entity.statistics.Form;
import com.oj.frameUtil.JqueryDataTableDto;
import com.oj.mapper.statistics.TodayMapper;
import com.oj.service.statistics.TodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TodayServicelmpl implements TodayService {
    @Autowired(required = false)
    private TodayMapper mapper;

    @Override
    public Map<String, String> getStatus(String college_id) {
        Map<String, String> map = new HashMap<>();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        String form_date = dateFormat.format(calendar.getTime());
        int allStudent = mapper.allStudent(college_id);
        int formStudent = mapper.formStudent(college_id,form_date);
        double baifenbi = 0;
        double yu = 0;
        if(formStudent==0||allStudent==0)
        {
            baifenbi = 0;
        }
        else
        {
            baifenbi = (double)(Math.round(formStudent*100/allStudent)/100.00)*100;
        }
        yu = 100-baifenbi;
        String allStudentS = Integer.toString(allStudent);
        String formStudentS = Integer.toString(formStudent);
        String baifenbiS = Double.toString(baifenbi);
        String yuS = Double.toString(yu);
        map.put("allStudent", allStudentS);
        map.put("formStudent", formStudentS);
        map.put("baifenbi", baifenbiS);
        map.put("yu", yuS);
        return map;
    }

    @Override
    public List<Map> getStatusMaplist(String college_id) {
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
        return mapper.formStudentList(college_id,Long.toString(zero),Long.toString(twelve));
    }

    @Override
    public List<Map> getNoStatusMaplist(String college_id) {
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        String form_date = dateFormat.format(calendar.getTime());
        return mapper.noStudentList(college_id,form_date);
    }
}
