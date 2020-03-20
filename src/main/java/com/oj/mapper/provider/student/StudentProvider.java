package com.oj.mapper.provider.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

public class StudentProvider {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public String getQuerySql(Map<String, Object> params){
        Map<String, String> inf = (Map<String, String>)params.get("condition");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT stu.student_id, stu.`account`, " +
                "stu.`name` AS student_name , cla.`name` AS class_name, " +
                "cla.`class_id` AS class_id ,college.`college_id`,college.`name` AS college_name FROM teach_student stu , " +
                "teach_class cla, teach_college college WHERE stu.class_id = cla.class_id AND stu.college_id = college.college_id");
        if(!StringUtils.isEmpty(inf.get("id")))
            sql.append(" AND stu.student_id = "+inf.get("id"));
        if(!StringUtils.isEmpty(inf.get("account")))
            sql.append(" AND stu.account LIKE '%"+inf.get("account")+"%' ");
        if(!StringUtils.isEmpty(inf.get("name")))
            sql.append(" AND stu.name LIKE '%"+inf.get("student_name")+"%' ");
        if(!StringUtils.isEmpty(inf.get("class_id")))
            sql.append(" AND stu.class_id = '"+inf.get("class_id")+"' ");
        if(!StringUtils.isEmpty(inf.get("college_id")))
            sql.append(" AND stu.college_id = '"+inf.get("college_id")+"' ");
        sql.append(" ORDER by stu.account DESC");
        log.info(sql.toString());
        return sql.toString();
    }
}
