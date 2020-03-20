package com.oj.mapper.provider.college;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

public class CollegeProvider {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    public String getQuerySql(Map<String, Object> params) {

        Map<String, String> info = (Map<String, String>)params.get("condition");
        StringBuffer sql = new StringBuffer();
        /*SELECT class.id, class.name, major.name as major_name, grade.name as grade_name FROM (teach_class class LEFT JOIN teach_major major ON class.major_id = major.id) LEFT JOIN teach_grade grade ON class.grade_id = grade.id ORDER BY class.id DESC*/

        sql.append(" SELECT * FROM teach_college");
        if (!StringUtils.isEmpty(info.get("name"))){
            sql.append(" where name like '%"+info.get("name")+"%' ");
        }
        sql.append(" order by college_id desc");
        System.out.println(sql);
        log.info(sql.toString());
        return sql.toString();
    }
}
/*
*/