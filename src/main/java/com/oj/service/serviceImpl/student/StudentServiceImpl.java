package com.oj.service.serviceImpl.student;

import com.oj.entity.student.BulkAddStudentPackage;
import com.oj.entity.student.Student;
import com.oj.entity.student.NewStu;
import com.oj.mapper.student.StudentMapper;
import com.oj.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired(required = false)
    private StudentMapper mapper;

    @Override
    public List<Map> getTargetStudentList(Map<String, String> param){
        List<Map> result = null;
        result = mapper.getTargetStudent(param);
        return result;
    }
    @Override
    public Map<String, String> getTheStudentById(String id){
        return mapper.getTheStudentById(id);
    }
    @Override
    public List<Map> getClassList(String college_id){
        return mapper.getClassList(college_id);
    }

    /*
    添加一个学生账号：
     */
    @Override
    public int addNewStudent(Student student) throws Exception {
        Map<String, String> map = mapper.getTheStudentByAccount(student.getAccount(),student.getCollege_id());
        if(null!=map && map.size()>0)
            throw new Exception("学号"+student.getAccount()+"已存在");
        return mapper.addNewStudent(student);
    }

    /*
    批量添加学生账号:
    参数说明：详情请看BulkAddStudentPackage 类的内部属性
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int addMoreNewStudent(BulkAddStudentPackage basp) throws Exception{
        //多low哦，但是为了精确定位到出错的位置，所以不得已而为之....
        for(NewStu ns : basp.getData()){
            try {
                addNewStudent(new Student(ns.getAccount(),  ns.getName(), basp.getClass_id(),basp.getCollege_id()));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public void deleteTheStudentById(String id){
        mapper.deleteTheStudentById(id);
    }
    @Override
    public void deleteStudentByClassId(String classId){
        mapper.deleteStudentByClassId(classId);
    }

    /*
    更新指定学生账号的信息（姓名、学号、班级）
     */
    @Override
    public int updateTheStudentById(String id, Student student) throws Exception{
        //Map 类型下， Mapper返回的数据类型与数据库表内属性类型有关！map.get("id")返回的是int型数据
        Map map = mapper.getTheStudentByAccount(student.getAccount(),student.getCollege_id());
        if(null!=map && map.size()>0 && !((id.equals(""+map.get("student_id")) )) ) { //更新学生账号信息时，先查询有没有相同学号，但非当前账号的存在
            throw new Exception("该学号已存在");
        }
        student.setStudent_id(id);
        return mapper.updateTheStudent(student);
    }

    /*
    更新指定学生账号的密码
     */
//    @Override
//    public void updateTheStudentPWById(String id, String pw){
//        pw = OJPWD.OJPWDTOMD5(pw); //MD5加密
//        mapper.updateTheStudentPWById( id, pw);
//    }

    @Override
    public String getTheClassIdByName(String className){
        String result=null;
        Map map = mapper.getTheClassIdByName(className);
        result=""+map.get("id");
        return result;
    }
}
