<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layui/css/public.css" media="all">
    <style>
        .layui-form-item {
            width: auto;
            padding-right: 10px;
            line-height: 38px;
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label">养老保险：工资%</label>
                <div class="layui-input-inline">
                    <input type="text" name="endowment" value="${data.endowment}" class="layui-input">
                </div>

            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">失业保险：工资%</label>
                <div class="layui-input-inline">
                    <input type="text" name="unemployment" value="${data.unemployment}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">工伤保险：工资%</label>
                <div class="layui-input-inline">
                    <input type="text" name="employmentInjury" value="${data.employmentInjury}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生育保险：工资%</label>
                <div class="layui-input-inline">
                    <input type="text" name="maternity" value="${data.maternity}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">医疗保险：工资%</label>
                <div class="layui-input-inline">
                    <input type="text" name="medical" value="${data.medical}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">公积金：工资%</label>
                <div class="layui-input-inline">
                    <input type="text" name="accumulation" value="${data.accumulation}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-radius layui-btn-normal" lay-submit lay-filter="saveBtn">确认修改</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../../layui/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form', 'miniTab'], function () {
        let form = layui.form,
            $ = layui.jquery,
            layer = layui.layer,
            miniTab = layui.miniTab;


        //监听提交
        form.on('submit(saveBtn)', function (data) {
            data = data.field;
            $.ajax({
                url: "edit",
                data: data,
                type: "post",
                success: function (data) {
                    if (data.type == "success") {
                        layer.msg(data.msg, {icon: 1});
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
