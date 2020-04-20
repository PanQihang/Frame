package com.oj.mapper.statistics;
import com.oj.entity.statistics.Form;
import org.apache.ibatis.annotations.*;
import com.oj.mapper.provider.statistics.SubmitProvider;
import java.util.List;
import java.util.Map;
@Mapper
public interface TodayMapper {
    @Select("select count(*) from teach_student where college_id = #{college_id}")
    public int allStudent(@Param("college_id") String college_id);

    @Select("select count(*) from teach_student where college_id = #{college_id} and form_date = #{form_date}")
    public int formStudent(@Param("college_id") String college_id,@Param("form_date") String form_date);

    @Select("SELECT teach_form.form_id,teach_form.is_headache,teach_form.temperature,teach_form.create_time," +
            "teach_student.`name` as student_name,teach_class.`name` as  class_name,teach_college.`name` as college_name," +
            "teach_student.account FROM teach_form ,teach_student ,teach_class ,teach_college WHERE teach_form.student_id = " +
            "teach_student.student_id AND teach_form.class_id = teach_class.class_id AND " +
            "teach_form.college_id = teach_college.college_id AND teach_form.college_id = #{college_id} " +
            "AND teach_form.create_time >= #{start} AND teach_form.create_time <= #{end} ORDER BY teach_form.create_time DESC")
    public List<Map> formStudentList(@Param("college_id") String college_id,@Param("start") String start,
                               @Param("end") String end);

    @Select("SELECT\n" +
            "teach_student.`name` AS student_name,\n" +
            "teach_student.account,\n" +
            "teach_college.`name` AS college_name,\n" +
            "teach_class.`name` AS class_name\n" +
            "FROM\n" +
            "teach_student ,\n" +
            "teach_college ,\n" +
            "teach_class\n" +
            "WHERE\n" +
            "teach_student.college_id = teach_college.college_id AND\n" +
            "teach_student.class_id = teach_class.class_id AND teach_student.college_id = #{college_id} " +
            "AND teach_student.form_date <> #{form_date}")
    public List<Map> noStudentList(@Param("college_id") String college_id,@Param("form_date") String form_date);
}
