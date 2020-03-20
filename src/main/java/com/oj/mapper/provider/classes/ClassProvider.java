package com.oj.mapper.provider.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

public class ClassProvider {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    public String getQuerySql(Map<String, Object> params) {

        Map<String, String> info = (Map<String, String>)params.get("condition");
        StringBuffer sql = new StringBuffer();
        /*SELECT class.id, class.name, major.name as major_name, grade.name as grade_name FROM (teach_class class LEFT JOIN teach_major major ON class.major_id = major.id) LEFT JOIN teach_grade grade ON class.grade_id = grade.id ORDER BY class.id DESC*/

        sql.append(" SELECT\n" +
                "teach_class.class_id,\n" +
                "teach_class.college_id,\n" +
                "teach_class.`name`,\n" +
                "teach_college.college_id,\n" +
                "teach_college.`name` as college_name \n" +
                "FROM\n" +
                "teach_class ,\n" +
                "teach_college ");
        sql.append(" WHERE teach_class.college_id = teach_college.college_id");
        if (!StringUtils.isEmpty(info.get("name"))){
            sql.append(" AND teach_class.name like '%"+info.get("name")+"%' ");
        }
        if (!StringUtils.isEmpty(info.get("college_id"))){
            sql.append(" AND teach_class.college_id = '"+info.get("college_id")+"' ");
        }
        sql.append(" order by teach_class.class_id desc");
        System.out.println(sql);
        log.info(sql.toString());
        return sql.toString();
    }
}
/*
*/