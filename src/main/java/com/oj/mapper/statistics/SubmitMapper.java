package com.oj.mapper.statistics;
import com.oj.entity.statistics.Form;
import org.apache.ibatis.annotations.*;
import com.oj.mapper.provider.statistics.SubmitProvider;
import java.util.List;
import java.util.Map;
@Mapper
public interface SubmitMapper {
    //@Select("SELECT a.problem_id,b.name,a.submit_state,a.submit_language,a.submit_time,a.submit_memory,a.submit_code_length,a.submit_date FROM teach_submit_code as a,teach_students as b where a.user_id=b.id order by submit_date desc limit 100")
    @SelectProvider(type=SubmitProvider.class, method = "getQuerySql")
    public List<Map> getSubmitStatusMaplist(@Param("condition")Map<String, String> params);
    // 查询总数
    @Select("SELECT COUNT(*) FROM teach_form AS a, teach_student AS b WHERE a.student_id = b.student_id")
    public int selectTotalCount();
    @SelectProvider(type=SubmitProvider.class, method = "selectRecordsFiltered")
    // 根据条件获取筛选后的总数
    public int selectRecordsFiltered(@Param("condition")Map<String, String> params);

    @Select("SELECT student_id from teach_student where college_id = #{college_id} and account = #{student_account} " +
            " and name = #{student_name}")
    public String selectStudentIdAccount(@Param("college_id") String college_id,@Param("student_account")
            String student_account, @Param("student_name") String student_name);

    @Select("SELECT count(*) from teach_student where college_id = #{college_id} and account = #{student_account}" +
            " and name = #{student_name}")
    public int is_student(@Param("college_id") String college_id,@Param("student_account") String student_account,
                          @Param("student_name") String student_name);

    @Insert("insert into teach_form (student_id,class_id,college_id,is_headache,temperature,create_time) " +
            "values(#{student_id},#{class_id},#{college_id},#{is_headache},#{temperature},#{create_time})")
    public int insertForm(@Param("student_id") String student_id,@Param("class_id") String class_id,
                          @Param("college_id") String college_id,@Param("is_headache") String is_headache,
                          @Param("temperature") String temperature,@Param("create_time") String create_time);

    @Update("UPDATE teach_student SET form_date=#{form_date} where student_id=#{student_id}")
    public void updateStudent(@Param("form_date") String form_date,@Param("student_id") String student_id);

    @Select("select form_date from teach_student where student_id = #{student_id}")
    public String selectFormDate(@Param("student_id") String student_id);
}
