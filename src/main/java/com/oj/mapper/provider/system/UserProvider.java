package com.oj.mapper.provider.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author lixu
 * @Time 2019年3月11日 18点15分
 * @Description 与User表相关动态sql生成
 */
public class UserProvider {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    public String getQuerySql(Map<String, Object> params){
        Map<String, String> info = (Map<String, String>)params.get("condition");
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT\n" +
                "\t(@i := @i + 1) AS num,\n" +
                "\tt1.*\n" +
                "FROM\n" +
                "\t(\n" +
                "SELECT\n" +
                "teach_admin.id,\n" +
                "teach_admin.account,\n" +
                "teach_admin.`name`,\n" +
                "teach_admin.role,\n" +
                "teach_back_role.role_name,\n" +
                "teach_college.`name` as college_name\n" +
                "FROM\n" +
                "teach_admin ,\n" +
                "teach_back_role ,\n" +
                "teach_college\n" +
                "WHERE\n" +
                "teach_admin.role != '32' AND \n" +
                "teach_admin.role = teach_back_role.id AND\n" +
                "teach_admin.college_id = teach_college.college_id");
        if (!StringUtils.isEmpty(info.get("id"))){
            sql.append(" AND teach_admin.id = '"+info.get("id")+"' ");
        }
        if (!StringUtils.isEmpty(info.get("account"))){
            sql.append(" AND teach_admin.account like '%"+info.get("account")+"%' ");
        }
        if (!StringUtils.isEmpty(info.get("name"))){
            sql.append(" AND teach_admin.name like '%"+info.get("name")+"%' ");
        }
        if (!StringUtils.isEmpty(info.get("role"))){
            sql.append(" AND teach_admin.role = '"+info.get("role")+"' ");
        }
        if (!StringUtils.isEmpty(info.get("college_id"))){
            sql.append(" AND teach_admin.college_id = '"+info.get("college_id")+"' ");
        }
        sql.append(" ) t1,( SELECT @i := 0 ) t2 ");
        log.info(sql.toString());
        return sql.toString();

    }
}
