package com.oj.mapper.classes;

import com.oj.entity.classes.Class;
import com.oj.mapper.provider.classes.ClassProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClassMapper {
    //通过ClassProvider类中的getQuerySql()方法动态构建查询语句
    @SelectProvider(type= ClassProvider.class, method = "getQuerySql")
    //查询课程结果，返回Map类型List
    public List<Map> getClassMapList(@Param("condition")Map<String, String> param);


    //获取学院下拉信息
    @Select("SELECT college_id, name FROM teach_college ")
    public List<Map> getCollegeSelectInfo();

//    //获取年级下拉信息
//    @Select("SELECT id, name FROM teach_grade order by name desc")
//    public List<Map> getGradeSelectInfo();
    //通过id获取对应的班级信息
    @Select("select * from teach_class where id = #{id}")
    public List<Map> getClassById(@Param("id") String id);

    //通过name获取对应的班级信息
    @Select("select * from teach_class where name = #{name} AND college_id = #{college_id}")
    public List<Map> getClassByName(@Param("name") String name,@Param("college_id") String college_id);

    //保存班级
    @Insert("insert into teach_class (name, college_id) values(#{name}, #{college_id})")
    @Options(useGeneratedKeys=true, keyProperty="class_id",keyColumn="class_id")
    public int save(Class clas);

    //更新班级
    @Update("update teach_class set name=#{name}, major_id=#{major_id}, grade_id=#{grade_id} where id = #{id}")
    public int update(Class clas);

    //删除班级
    @Delete("delete from teach_class where class_id = #{class_id}")
    public void classDelete(@Param("class_id") String class_id);

    //获取学生列表
    @Select("select student.account as student_account, student.name as student_name " +
            "from teach_student student, teach_class class where student.class_id = class.class_id " +
            "and class.class_id = #{id} order by student.account;")
    public List<Map> getStudentMapByClassList(@Param("id") String id);
}
