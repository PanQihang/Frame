$(document).ready(function () {
    loadLayerDate();
    getRole();
    getStatusInfo();
    indexClassNameSelect();
});
function loadLayerDate() {
    var startTime;
    var endTime;

    startTime = laydate.now();
    endTime = laydate.now();
    // $('#experStartTime').val(laydate.now(0, 'YYYY-MM-DD hh:mm:ss'));
    // $('#experEndTime').val(laydate.now(7, 'YYYY-MM-DD hh:mm:ss'));

    //日期范围限制
    var start = {
        elem: '#experStartTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        // start:startTime,
        // min: startTime, //设定最小日期为当前日期
        // max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };

    var end = {
        elem: '#experEndTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        // start:endTime,
        // min: startTime,
        // max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
}
var role = 0;
var is_headache = [
    "否", //0
    "是", //1
]
//重置form内的标签
function resetForm() {
    $(".form-horizontal input").val("");
    $(".form-horizontal select").val("");
    getStatusInfo();
}
function getRole() {
    $.ajax({
        type: "POST",
        url: "/classesMn/getRole",
        dataType: "json",
        success:function (result){
            if(result.role=="1")
            {
                role = 1;
                indexCollegeNameSelect();
            }
            else
            {
                role = 0;
                document.getElementById("selectCollege").style.display="none";
            }
        }
    })
}
//获取所有学院信息
function indexCollegeNameSelect() {
    $.ajax({
        type: "POST",
        url: "/classesMn/getCollegeSelectInfo",
        dataType: "json",
        success:function (result){
            var roleSelectInfo = "";
            for (var i=0; i<result.length; i++){
                roleSelectInfo += "<option value='"+result[i].college_id+"'>"+result[i].name+"</option>"
            }
            $("#collegeName").append(roleSelectInfo);
            $("#dialogMajorName").append(roleSelectInfo);
            $("#dialogMajorNameB").append(roleSelectInfo);
        }
    })
}
//获取所有班级信息
function indexClassNameSelect(id) {
    $.ajax({
        type: "POST",
        url: "/studentMn/getClassList",
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
            $("#classId").append(roleSelectInfo);
        }
    })
}
//清除Option
function removeOption(id){
    var obj=document.getElementById(id);
    obj.options.length=0;
}
function dateToStr(date) {
    var s = new Date(date);
    return s.getTime();
}
function getStatusInfo() {
    $.ajax({
        type: "POST",
        url: "/submitMn/getSubmitStatusMaplist",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify({
            "name" : $('#studentName').val(),
            "account" : $('#account').val(),
            "college_id" : $('#collegeName').val(),
            "class_id" : $('#classId').val(),
            "is_headache" : $('#is_headache').val(),
            "start":dateToStr($("#experStartTime").val()),
            "end":dateToStr($("#experEndTime").val()),
        }),
        success:function (result) {
            var dataTable = $('#StatusInfoTable');
            if ($.fn.dataTable.isDataTable(dataTable)) {
                dataTable.DataTable().destroy();
            }
            dataTable.DataTable({
                "serverSide": false,
                "autoWidth" : false,
                "bSort": false,
                "data" : result,
                "columns": [{
                    "data": "form_id"
                }, {
                    "data": "student_name"
                },{
                    "data": "account"
                }, {
                    "data": "college_name"
                }, {
                    "data": "class_name"
                }, {
                    "data": "temperature"
                }, {
                    "data": "is_headache"
                }, {
                    "data": "create_time"
                }],
                "columnDefs": [{
                    "render" : function(data, type, row) {
                        var a = "";
                        if(undefined != is_headache[row.is_headache]){
                            a = is_headache[row.is_headache]
                        }
                        return a;
                    },
                    "targets" :6
                },{
                    "render" : function(data, type, row) {
                        var a = "";
                        a = format(row['create_time'])
                        return a;
                    },
                    "targets" :7
                }]
            });
        }
    })
}

function format(time)
{
    return new Date(parseInt(time)).toLocaleString().replace(/:\d{1,2}$/,' ');
}



