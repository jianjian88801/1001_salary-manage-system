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
<div class="layui-form layuimini-form" lay-filter="editForm">
    <div class="layui-form-item">
        <label class="layui-form-label">部门编号</label>
        <div class="layui-input-block">
            <input type="text" name="deptId" disabled readonly
                   value="" class="layui-input layui-disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">部门名称</label>
        <div class="layui-input-block">
            <input type="text" name="deptName"
                   value="" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <%--<label class="layui-form-label">负责人工号</label>
        <div class="layui-input-block">
            <input type="text" name="workId"  value="" class="layui-input">
        </div>--%>
        <label class="layui-form-label required">负责人</label>
        <div class="layui-input-inline">
            <select name="workId" id="workId">
                <option value="">请选择...</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label ">部门电话</label>
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
    function child(data) {
        layui.use(['form'], function () {
            let form = layui.form,
                layer = layui.layer,
                $ = layui.$;

            form.val("editForm", {
                "deptId": data.deptId// "name": "value"
                , "deptName": data.deptName
                // , "workId": data.workId
                , "deptPhone": data.deptPhone
                , "remark": data.remark
            });

            $(function () {
                getWorkId();
            })

            function getWorkId() {
                $.ajax({
                    url: '../dept/get_staff',
                    data: {"deptId": data.deptId},
                    type: 'get',
                    success: function (result) {
                        let res = result.rows;
                        console.log(res);
                        for (let i = 0; i < res.length; i++) {
                            $("#workId").append("<option value=\"" + res[i].workId + "\">" + res[i].workId +"(" +res[i].trueName+")" +"</option>");
                        }
                        $("#workId").find("option[value = "+data.workId+"]").attr("selected",true);
                        form.render('select');//刷新select选择渲染

                    }
                })
            }

            //监听提交
            form.on('submit(saveBtn)', function (data) {
                data = data.field;
                $.ajax({
                    url: "edit",
                    data: data,
                    type: "post",
                    success: function (data) {
                        if (data.type == "success") {
                            layer.msg(data.msg, {icon: 1}, function () {
                                setTimeout(function () {
                                    let index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                }, 1000)
                            });
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    }
                })

                return false;
            });

        });
    }

</script>
</body>
</html>
