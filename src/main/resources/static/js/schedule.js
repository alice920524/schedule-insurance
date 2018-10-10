/**
 * Created by admin on 2016/10/24.
 */
$(function(){
    var _pagination = new Pagination(),
        pageLoadIndex = 1;
    var params;
    var tableObj = $("#activity-list-table");
    $("body").on("click","#searchBtn",function(){
        params = new Object();
        var type = $(".type").val();
        $("#projectKey").find("[data-name=type]").val(type);
        if($("#scheduleStatus").is(':checked')){
            $("#projectKey").find("[data-name=status]").val(-1);
            params["status"] = -1;
        }
        if(type){
            params["type"] = type;
        }
        if ($(this)[0].tagName == 'INPUT') {
            params["page"] = 1;
        } else {
            params["page"] = $(this).attr("data_no");
        }
        params["size"] = 5;

        findPage(params);
    });

    $("#searchBtn").click();

    //重新调度-数据恢复
    $("body").on("click",".restore",function(){
        var val = $(this).parents("tr").data("id");
        $.ajax({url:"/restore/restore",data:{id:val},type:"POST",success:function(data){
            alert(data.message);
            $(".pagination .active").click();
        }});
    });

    $("#pagination").on("click", "li", function(){
        params["page"] = $(this).attr("data_no");
        findPage(params);
    });

    function findPage(params) {
        $.ajax({
            url: "/restore/page",
            async: true,
            type: "POST",
            dataType:"json",
            data: params,
            success:function(data) {
                if (data.code != -1) {
                    var tableHtml = '';
                    var records = data.data.list;
                    if (data.data != null && data.data.list != null && data.data.list.length > 0) {
                        //分页
                        $("#countInfo").html("第 "+data.data.pageNum+" 页 ( 总共 "+data.data.pages+" 页 ,共 "+data.data.total+" 项)");
                        var pageNo = data.data.pageNum,
                            totalPage = data.data.pages,
                            pageSize;
                        if (totalPage == 1) {
                            $("#pagination").hide();
                            $("#pagination_onePage").show();
                            pageSize = records.length;
                        } else {
                            $("#pagination").show();
                            $("#pagination_onePage").hide();
                            _pagination.initPage(pageNo,totalPage,5,records);
                        }
                        //数据显示
                        for (var i = 0;i < records.length;i++) {
                            tableHtml = tableHtml + '<tr data-id="' + records[i].id + '">' +
                                '<td>' + records[i].id + '</td>' +
                                '<td>' + records[i].type + '</td>' +
                                '<td>' + (new Date(records[i].scheduleTime)).Format("yyyy-MM-dd hh:mm") + '</td>' +
                                '<td>' + records[i].classType + '</td>' +
                                '<td>' + records[i].remark + '</td>';
                            var operBtn = '';
                            if (records[i].status == 1) {
                                tableHtml = tableHtml + '<td>调度成功</td>';
                            } else if (records[i].status < 1) {
                                tableHtml = tableHtml + '<td>调度失败</td>';
                                operBtn = '<td><span class="restore">重新调度</span></td>';
                            }
                            tableHtml = tableHtml + '<td>' + records[i].executeTime + '</td>' + operBtn + '</tr>';
                        }
                    } else {
                        $("#countInfo").html("");
                        $("#pagination").html("");
                        tableHtml = '<tr><td colspan="8" align="center">没有获取到数据！</td></tr>';
                    }
                } else if (data.code = -1) {
                    tableHtml = '<tr><td colspan="8" align="center">查询异常！</td></tr>';
                }
                tableObj.find("tbody").html(tableHtml);
            }
        });
    }

    Date.prototype.Format = function(fmt)
    { //author: meizz
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
});