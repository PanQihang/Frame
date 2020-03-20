package com.oj.mapper.college;

import com.oj.entity.college.College;
import com.oj.mapper.provider.college.CollegeProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CollegeMapper {
    //通过ClassProvider类中的getQuerySql()方法动态构建查询语句
    @SelectProvider(type= CollegeProvider.class, method = "getQuerySql")
    //查询课程结果，返回Map类型List
    public List<Map> getCollegeMapList(@Param("condition")Map<String, String> param);

    //通过id获取对应的班级信息
    @Select("select * from teach_class where id = #{id}")
    public List<Map> getClassById(@Param("id") String id);

    //通过name获取对应的班级信息
    @Select("select * from teach_class where name = #{name}")
    public List<Map> getClassByName(@Param("name") String name);

    //保存学院
    @Insert("insert into teach_college (name) values(#{name}")
    @Options(useGeneratedKeys=true, keyProperty="id",keyColumn="id")
    public int save(College clas);

    //更新学院
    @Update("update teach_college set name=#{name} where id = #{id}")
    public int update(College clas);

    //删除学院
    @Delete("delete from teach_college where id = #{id}")
    public void collegeDelete(String id);

    //获取班级列表
    @Select("select students.account as student_account, students.name as student_name from teach_students students, teach_class class where students.class_id = class.id and class.id = #{id} order by students.account;")
    public List<Map> getStudentMapByClassList(String id);
}
