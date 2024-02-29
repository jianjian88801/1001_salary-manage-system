<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>底薪</title>
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
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
        <script type="text/html" id="toolbarDemo">
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

    </div>
</div>
<script src="../../layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'layer'], function () {
        let $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            toolbar: '#toolbarDemo',
            url: 'get_base_list',
            cols: [[
                // {type: "checkbox", width: 50},
                {type: 'numbers', title: "序号"},
                {field: 'id', hide: true},
                {field: 'workId', title: '工号', sort: true,width:150},
                {field: 'trueName', title: '姓名', sort: true},
                {field: 'baseSalary', title: '底薪', sort: true, edit: 'text'}
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
            skin: 'line'
        });
        //监听单元格编辑
        table.on('edit(currentTableFilter)', function (obj) {

            let data = obj.data;

            $.ajax({
                url: "edit_base",
                data: data,
                type: "post",
                success: function (data) {
                }
            })
        });
        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            data = data.field;

            //执行搜索重载
            table.reload('currentTableId', {
                url: 'get_base_list',
                page: {
                    curr: 1
                },
                where: {
                    workId: data.workId,
                    trueName: data.trueName
                }
            }, 'data');

            return false;
        });


    });
</script>

</body>
</html>
