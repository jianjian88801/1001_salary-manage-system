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
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">部门编号</label>
        <div class="layui-input-block">
            <input type="text" name="deptId" lay-verify="required" lay-reqtext="部门编号不能为空" placeholder="请输入部门编号"
                   value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">部门名称</label>
        <div class="layui-input-block">
            <input type="text" name="deptName" lay-verify="required" lay-reqtext="部门名称不能为空" placeholder="请输入部门名称"
                   value="" class="layui-input">
        </div>
    </div>

    <%--<div class="layui-form-item">
        <label class="layui-form-label">负责人工号</label>
        <div class="layui-input-block">
            <input type="text" name="workId" placeholder="请输入负责人工号" value="" class="layui-input">
        </div>

    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">部门电话</label>
        <div class="layui-input-block">
            <input type="text" name="deptPhone"
                   value="" class="layui-input" maxlength="11">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注信息</label>
        <div class="layui-input-block">
            <textarea name="remark" class="layui-textarea" placeholder="请输入备注信息"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../../layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        let form = layui.form,
            layer = layui.layer,
            $ = layui.$;



        //监听提交
        form.on('submit(saveBtn)', function (data) {
            data = data.field;
            $.ajax({
                url: "add",
                data: data,
                type: "post",
                success: function (data) {
                    if (data.type === "success") {
                        layer.msg(data.msg, {icon: 1}, function () {
                            setTimeout('window.location.reload()', 1000);
                            let index = parent.layer.getFrameIndex(window.name);
                            parent.layui.table.reload('deptTableId');
                            parent.layer.close(index);
                        });
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }

                }
            })

            return false;
        });

    });
</script>
</body>
</html>
