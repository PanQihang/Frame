package com.oj.mapper.provider.statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

public class SubmitProvider {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public String getQuerySql(Map<String,Object> params){
        Map<String, String> info = (Map<String, String>)params.get("condition");
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT\n" +
                "teach_form.form_id,\n" +
                "teach_form.is_headache,\n" +
                "teach_form.temperature,\n" +
                "teach_form.create_time,\n" +
                "teach_student.`name` as student_name,\n" +
                "teach_class.`name` as  class_name,\n" +
                "teach_college.`name` as college_name,\n" +
                "teach_student.account\n" +
                "FROM\n" +
                "teach_form ,\n" +
                "teach_student ,\n" +
                "teach_class ,\n" +
                "teach_college\n" +
                "WHERE\n" +
                "teach_form.student_id = teach_student.student_id AND\n" +
                "teach_form.class_id = teach_class.class_id AND\n" +
                "teach_form.college_id = teach_college.college_id ");
        if(!StringUtils.isEmpty(info.get("student_name"))){
            sql.append("		AND teach_student.name like '%"+info.get("student_name")+"%' ");
        }
        if(!StringUtils.isEmpty(info.get("account"))){
            sql.append("		AND teach_student.account like '%"+info.get("account")+"%' ");
        }
        if(!StringUtils.isEmpty(info.get("college_id"))){
            sql.append("		AND teach_form.college_id = '"+info.get("college_id")+"' ");
        }
        if(!StringUtils.isEmpty(info.get("class_id"))){
            sql.append("		AND teach_form.class_id = '"+info.get("class_id")+"' ");
        }
        if(!StringUtils.isEmpty(info.get("is_headache"))){
            sql.append("		AND teach_form.is_headache = '"+info.get("is_headache")+"' ");
        }if(!StringUtils.isEmpty(info.get("start"))){
            sql.append("		AND teach_form.create_time >= '"+info.get("start")+"' ");
        }if(!StringUtils.isEmpty(info.get("end"))){
            sql.append("		AND teach_form.create_time <= '"+info.get("end")+"' ");
        }
        sql.append(" 		ORDER BY		 ");
        sql.append(" 			teach_form.create_time DESC 	 ");
//        sql.append(" 			LIMIT "+info.get("start")+","+info.get("count")+"	 ");
        log.info(sql.toString());
        return sql.toString();

    }

    public String selectRecordsFiltered(Map<String,Object> params){
        Map<String, String> info = (Map<String, String>)params.get("condition");
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT\n" +
                "count(*) \n" +
                "FROM\n" +
                "teach_form ,\n" +
                "teach_student ,\n" +
                "teach_class ,\n" +
                "teach_college\n" +
                "WHERE\n" +
                "teach_form.student_id = teach_student.student_id AND\n" +
                "teach_form.class_id = teach_class.class_id AND\n" +
                "teach_form.college_id = teach_college.college_id ");
        if(!StringUtils.isEmpty(info.get("student_name"))){
            sql.append("		AND teach_student.name like '%"+info.get("student_name")+"%' ");
        }
        if(!StringUtils.isEmpty(info.get("account"))){
            sql.append("		AND teach_student.account like '%"+info.get("account")+"%' ");
        }
        if(!StringUtils.isEmpty(info.get("college_id"))){
            sql.append("		AND teach_form.college_id = '"+info.get("college_id")+"' ");
        }
        if(!StringUtils.isEmpty(info.get("class_id"))){
            sql.append("		AND teach_form.class_id = '"+info.get("class_id")+"' ");
        }
        if(!StringUtils.isEmpty(info.get("is_headache"))){
            sql.append("		AND teach_form.is_headache = '"+info.get("is_headache")+"' ");
        }
        sql.append(" 		ORDER BY		 ");
        sql.append(" 			teach_form.create_time DESC 	 ");
        log.info(sql.toString());
        return sql.toString();
    }

}
