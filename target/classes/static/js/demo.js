$(document).ready(function () {
    var s = laydate.now(0, 'YYYY/MM/DD')+' 健康报备';
    document.getElementById("head").innerHTML=s;
    indexCollegeSelect()
});
function indexCollegeSelect() {
    $.ajax({
        type: "POST",
        url: "/form/getCollegeSelectInfo",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success:function (result){
            var roleSelectInfo = "";
            for (var i=0; i<result.length; i++){
                roleSelectInfo += "<option value='"+result[i].college_id+"'>"+result[i].name+"</option>"
            }
            $("#dialogMajorName").append(roleSelectInfo);
        }
    })
}
//获取所有班级信息
function indexClassNameSelect(id) {
    $.ajax({
        type: "POST",
        url: "/form/getClassList",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify({
            "college_id" : id
        }),
        success:function (result){
            var roleSelectInfo = "";
            for (var i=0; i<result.length; i++){
                roleSelectInfo += "<option value='"+result[i].class_id+"'>"+result[i].name+"</option>"
            }
            $("#dialogClassName").append(roleSelectInfo);

        }
    })
}

function submit() {
    var XTypeRule = /^[0-9]*$/;
    var tiwen = /((3[5-9])|40).\d/;
    var college_id = $("#dialogMajorName").val();
    var class_id = $("#dialogClassName").val();
    var student_name = $("#dialogStudentName").val();
    var student_account = $("#dialogStudentAccount").val();
    var temperature = $("#dialogTemperature").val();
    var is_headache = $("#is_headache").val();
    if(college_id=="" || college_id == null)
    {
        swal("请选择学院", "", "error");
        return;
    }if(class_id=="" || class_id == null)
    {
        swal("请选择班级", "", "error");
        return;
    }if(student_name=="" || student_name == null)
    {
        swal("请输入学生姓名", "", "error");
        return;
    }if(student_account=="" || student_account == null)
    {
        swal("请输入学号", "", "error");
        return;
    }if(temperature=="" || temperature == null)
    {
        swal("请输入体温", "", "error");
        return;
    }if(is_headache=="" || is_headache == null)
    {
        swal("请选择是否头疼", "", "error");
        return;
    }if(!XTypeRule.test(student_account)  || student_account.length>20 || student_account.length<1) {
        swal('学号格式不正确', '请确保为长度不大于20的非空数字', 'error');
        return;
    }if(student_name.length>20 || student_name.length<1){
        swal('姓名格式不正确', '请确保为长度不大于20的非空字符串', 'error');
        return;
    }if(!tiwen.test(temperature)){
        swal('体温格式不正确', '', 'error');
        return;
    }$.ajax({
        type: "POST",
        url: "/form/submitForm",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify({
            "college_id" : college_id,
            "class_id" : class_id,
            "student_name" : student_name,
            "student_account" : student_account,
            "temperature" : temperature,
            "is_headache" : is_headache,
        }),
        success:function (result){
            if(result.result=='success')
            {
                swal('提交成功', '', 'success');
            }
            else
            {
                swal('提交失败', result.message, 'error');
            }
        }
    })
}

