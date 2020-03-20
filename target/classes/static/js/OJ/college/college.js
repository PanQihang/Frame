$(document).ready(function () {
    queryCollegeInfo();
});
var icon = "<i class='fa fa-times-circle'></i>";
//重置form内的标签
function resetForm() {
    $(".form-horizontal input").val("");
    $(".form-horizontal select").val("");
    queryClassInfo();
}
function resetClassInfoDialog() {
    $("#myModal5 input").val("");
    $("#myModal5 select").val("");
    $("#myModal5 input").removeClass("error");
    $("#myModal5 select").removeClass("error");
    $("#myModal5 label.error").remove()
}
function queryCollegeInfo() {
    $.ajax({
        type: "POST",
        url: "/collegeMn/getCollegeMapList",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify({
            "name" : $('#collegeName').val(),
        }),
        success:function (result) {
            var dataTable = $('#collegeInfoTable');
            if ($.fn.dataTable.isDataTable(dataTable)) {
                dataTable.DataTable().destroy();
            }
            dataTable.DataTable({
                "serverSide": false,
                "autoWidth" : false,
                "bSort": false,
                "data" : result,
                "columns" : [{
                    "data" : "college_id"
                },{
                    "data" : "name"
                }],
                "columnDefs": [{
                    "render" : function(data, type, row) {
                        var a = "";
                        //a += "<button type='button' class='btn btn-primary' onclick='classShow(\""+row.id+"\")' data-toggle='modal' data-target='#classStudentShowDialog' title='班级列表' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-list'></i>&nbsp;班级列表</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='showEditCollege(\""+row.college_id+"\")' data-toggle='modal' data-target='#myModal5' title='编辑学院' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-pencil-square-o'></i>&nbsp;编辑</button>"
                        a += "<button type='button' class='btn btn-primary' onclick='deleteCollege(\""+row.college_id+"\")' title='删除学院' data-toggle='dropdown' style='margin-right:15px; margin-bottom: -1px;'><i class='fa fa-user-times'></i>&nbsp;删除</button>"
                        return a;
                    },
                    "targets" :2
                }]
            });
        }
    })
}
//展示班级编辑详情模态窗口
function showEditCollege(id) {
    //alert("1231231");
    resetClassInfoDialog();
    if(id!=''){
        $("#dialogTitle").html("编辑学院")
        $("#dialogClassId").val(id)
        $.ajax({
            type: "POST",
            url: "/collegeMn/getCollegeMapList",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                "id" : id
            }),
            success:function (result){
                //alert(JSON.stringify(result));
                $("#dialogCollegeName").val(result[0].name);
            }
        })
    }else{
        $("#dialogTitle").html("添加学院");
        $("#dialogCollegeName").val('');
    }
}


//新增或更新班级信息
function saveOrUpdateClassInfo() {
    if($("#dialogCollegeInfo").validate({
        rules: {
            dialogCollegeName: {
                required: true,
                maxlength: 10
            },
        },
        messages: {
            dialogCollegeName: {
                required: icon + "学院名不能为空",
                equalTo: icon + "班级名最长为10"
            },
        }
    }).form()){
        $.ajax({
            type: "POST",
            url: "/collegeMn/saveOrUpdateCollege",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                "id" : $("#dialogCollegeId").val(),
                "name" : $("#dialogCollegeName").val(),
            }),
            success:function (result){
                if(result.flag == 1){
                    queryClassInfo();
                    //关闭模态窗口
                    $('#myModal5').modal('hide');
                    swal("保存成功！","", "success");
                }else{
                    swal("保存失败！", result.message, "error");
                }
            }
        });
    }
}

function deleteCollege(id) {
    swal({
            title: "确认删除?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    type: "POST",
                    url: "/classMn/classDelete",
                    // contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    data:{
                        "id" : id
                    },
                    success:function (result){
                        if(result.flag == "1"){
                            queryClassInfo();
                            swal("删除成功！", "班级已被删除", "success");
                        }else{
                            swal("删除失败！", "班级暂时不能被删除", "error");
                        }

                    }
                })
            }else {
                swal("已取消", "你取消了删除班级操作", "error");
            }
        });
}
