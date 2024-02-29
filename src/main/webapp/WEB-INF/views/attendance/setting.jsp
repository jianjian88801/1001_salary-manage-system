<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>考勤奖惩</title>
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
                <label class="layui-form-label">迟到罚款：</label>
                <div class="layui-input-inline">
                    <input type="text" name="late" value="${data.late}" class="layui-input">
                </div>
                <div class="layui-input-inline">元/次</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">早退罚款：</label>
                <div class="layui-input-inline">
                    <input type="text" name="earlyLeave" value="${data.earlyLeave}" class="layui-input">
                </div>
                <div class="layui-input-inline">元/次</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">请假罚款：</label>
                <div class="layui-input-inline">
                    <input type="text" name="leave" value="${data.leave}" class="layui-input">
                </div>
                <div class="layui-input-inline">元/天</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">加班奖金：</label>
                <div class="layui-input-inline">
                    <input type="text" name="overtime" value="${data.overtime}" class="layui-input">
                </div>
                <div class="layui-input-inline">元/时</div>
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
                url: "edit_set",
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
