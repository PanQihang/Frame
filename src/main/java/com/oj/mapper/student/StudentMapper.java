package com.oj.mapper.student;
import com.oj.entity.student.Student;
import com.oj.entity.system.Auth;
import com.oj.mapper.provider.student.StudentProvider;
import com.oj.mapper.provider.system.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //查找学生
    ////通过姓名、学号、班级模糊查询符号的学生集
    @SelectProvider(type= StudentProvider.class, method = "getQuerySql")
    public List<Map> getTargetStudent(@Param("condition")Map<String, String> param);
    ////通过班级id查找学生集
    @Select("SELECT * FROM teach_student where class_id = #{classId} ORDER BY student_id DESC")
    public List<Map> getStudentListByClassId(@Param("classId") String classId);
    ////通过学生姓名查找学生集
    @Select("SELECT * FROM teach_student where name = #{name} ORDER BY student_id DESC")
    public List<Map> getStudentListByName(@Param("name") String name);
    ////获取所有学生
    @Select("SELECT * FROM teach_student ORDER BY student_id DESC")
    public List<Map> getStudentList();
    ////通过学生编号查找学生
    @Select("SELECT * FROM teach_student where student_id = #{id}")
    public Map getTheStudentById(@Param("id") String id);
    ////通过学生学号查找学生
    @Select("SELECT student_id, name, account, class_id FROM teach_student where account = #{account} AND college_id = #{college_id}")
    public Map getTheStudentByAccount(@Param("account") String account,@Param("college_id") String college_id);

    //查找班级
    @Select("SELECT * FROM teach_class where college_id = #{college_id}")
    public List<Map> getClassList(@Param("college_id") String college_id);

    //增加学生
    @Insert("INSERT INTO teach_student(account, name, class_id,college_id) values(#{account}, #{name}, #{class_id},#{college_id})")
    @Options(useGeneratedKeys=true, keyProperty="student_id",keyColumn="student_id")
    public int addNewStudent(Student student);

    //删除学生
    ////通过编号删除学生
    @Delete("DELETE FROM teach_student where student_id = #{id}")
    public void deleteTheStudentById(@Param("id") String id);
    ////通过学号删除学生
    @Delete("DELETE FROM teach_student where account = #{account}")
    public void deleteTheStudentByAccount(@Param("account") String account);
    ////通过班级id删除学生集
    @Delete("DELETE FROM teach_student where class_id = #{class_id}")
    public void deleteStudentByClassId(@Param("class_id") String class_id);

    //修改学生
    ////通过编号修改学生信息
    @Update("UPDATE teach_student set account = #{account}, name = #{name}, class_id = #{class_id}, college_id = #{college_id} " +
            "where student_id = #{student_id}")
    public int updateTheStudent(Student student);
//    ////通过编号修改学生密码
//    @Update("UPDATE teach_student set password = #{password} where id = #{id}")
//    public void updateTheStudentPWById( @Param(value = "id") String id, @Param(value = "password") String password);

    //查找班级
    @Select("SELECT class_id FROM teach_class where name = #{className}")
    public Map getTheClassIdByName(@Param(value = "className") String className);



}
