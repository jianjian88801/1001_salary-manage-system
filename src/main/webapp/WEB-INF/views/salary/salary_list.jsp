<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">工号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="workId" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="trueName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">时间年月：</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" name="times" autocomplete="off" id="times">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                    lay-filter="data-create-btn"><i class="layui-icon">&#xe63c; </i>生 成
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
        <script type="text/html" id="toolbarDemo">
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="read">详情</a>
        </script>

    </div>
</div>
<script src="../../layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'layer','laydate'], function () {
        let $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            laydate = layui.laydate,
            table = layui.table;

        //年月选择器
        let date = new Date();
        let nowDate = date.getFullYear()+"-"+(date.getMonth()+1);
        laydate.render({
            elem: '#times'
            ,max:nowDate
            , type: 'month'
        });

        table.render({
            elem: '#currentTableId',
            toolbar: '#toolbarDemo',
            url: 'get_list',
            cols: [[
                {type: "numbers",title: '序号',width: 80},
                {field: 'id',  title: 'ID',hide: true},
                {field: 'workId',width:150, title: '工号',sort: true},
                {field: 'trueName', title: '姓名', sort: true,width: 150},
                {field: 'times',  title: '时间',sort: true},
                {field: 'finalSalary', title: '本月实发工资'},
                {title: '操作',  toolbar: '#currentTableBar', align: "center"}
            ]],
            parseData: function (ret) {
                return {
                    "code": 0,
                    "msg": "",
                    "count": ret.total,
                    "data": ret.rows
                };
            },
            limits: [10],
            limit: 10,
            page: true,
            id: 'currentTableId',
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            let result = data.field;

            //执行搜索重载
            table.reload('currentTableId', {
                url: 'get_list',
                page: {
                    curr: 1
                }
                , where: {
                    workId: result.workId,
                    trueName: result.trueName,
                    times: result.times
                }
            }, 'data');

            return false;
        });
        //生成
        form.on('submit(data-create-btn)', function (data) {
            let result = data.field;
            if (result['times'] == '') {
                layer.alert('请选择时间');
            } else {
                //执行搜索重载
                table.reload('currentTableId', {
                    url: 'add',
                    page: {
                        curr: 1
                    }
                    , where: {
                        times: result.times
                    }
                }, 'data');
            }
            return false;
        });
        /**
         * toolbar监听事件
         */
        table.on('tool(currentTableFilter)', function (obj) {
            let data = obj.data;

            if (obj.event === 'read') {

                let index = layer.open({
                    title: '工资详情',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'info',
                    success: function(layero, index){
                        let iframe = window['layui-layer-iframe'+index];
                        iframe.child(data)
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            }
        });

    });
</script>

</body>
</html>
