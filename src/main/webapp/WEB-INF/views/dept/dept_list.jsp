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
                            <label class="layui-form-label">部门名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deptName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">负责人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deptManager" autocomplete="off" class="layui-input">
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
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon">&#xe608;</i> 添加
                </button>
            </div>
        </script>

        <table class="layui-hide" id="deptTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">
                <i class="layui-icon">&#xe642;</i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">
                <i class="layui-icon">&#xe640;</i>删除</a>
        </script>

    </div>
</div>
<script src="../../layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        let $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#deptTableId',
            url: 'get_list',
            toolbar: '#toolbarDemo',
            cellMinWidth: 80,
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: 'numbers', title: "序号"},
                {field: 'id', title: 'ID', hide: true},
                {field: 'deptId', title: '部门编号', sort: true},
                {field: 'deptName', title: '部门名称', sort: true},
                {field: 'workId', title: '负责人工号', sort: true},
                {field: 'deptManager', title: '负责人名'},
                {field: 'deptPhone', title: '部门电话'},
                {field: 'remark', title: '备注'},
                {title: '操作', toolbar: '#currentTableBar', align: "center"}
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
            skin: 'line',
            id: 'currentTableId'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            data = data.field;

            //执行搜索重载
            table.reload('currentTableId', {
                url: 'get_list',
                page: {
                    curr: 1
                },
                where: {
                    deptName: data.deptName,
                    deptManager: data.deptManager
                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                let index = layer.open({
                    title: '添加部门',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'add',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });


        table.on('tool(currentTableFilter)', function (obj) {
            let data = obj.data;
            if (obj.event === 'edit') {

                let index = layer.open({
                    title: '编辑部门',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'edit',
                    success: function(layero, index){
                        let iframe = window['layui-layer-iframe'+index];
                        iframe.child(data)
                    },
                    end:function (){
                        table.reload("currentTableId",{
                            page: {
                                curr: 1
                            }
                            },'data');
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                $.ajax({
                    url:'delete',
                    data:data,
                    type:'post',
                    success:function (data){
                        if (data.type === "success"){
                            layer.msg(data.msg,{icon: 1},function (){
                                table.reload("currentTableId",{url:'get_list'},'data');
                            });
                        }else {
                            layer.msg(data.msg,{icon:2});
                        }

                    }
                })
            }
        });
    });
</script>

</body>
</html>
