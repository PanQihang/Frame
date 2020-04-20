$(document).ready(function () {
    getStatus();
    getStatusInfo();
    getNoStatusInfo();
});
var is_headache = [
    "否", //0
    "是", //1
]
function getStatus() {
    $.ajax({
        type: "POST",
        url: "/todayMn/getStatus",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success:function (result) {
            var s = "填写进度:   "+result.formStudent+"/"+result.allStudent;
            $("#h3").text(s);
            $("#completeDiv").css("width",result.baifenbi + "%").text(result.baifenbi+"%已完成");
            $("#dangerDiv").css("width",result.yu + "%").text(result.yu+"%未完成");
        }
    })
}
function getStatusInfo() {
    $.ajax({
        type: "POST",
        url: "/todayMn/getStatusMaplist",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
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
function getNoStatusInfo() {
    $.ajax({
        type: "POST",
        url: "/todayMn/getNoStatusMaplist",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success:function (result) {
            var dataTable = $('#noStatusInfoTable');
            if ($.fn.dataTable.isDataTable(dataTable)) {
                dataTable.DataTable().destroy();
            }
            dataTable.DataTable({
                "serverSide": false,
                "autoWidth" : false,
                "bSort": false,
                "data" : result,
                "columns": [{
                    "data": "student_name"
                },{
                    "data": "account"
                }, {
                    "data": "college_name"
                }, {
                    "data": "class_name"
                }],
            });
        }
    })
}
function format(time)
{
    return new Date(parseInt(time)).toLocaleString().replace(/:\d{1,2}$/,' ');
}



