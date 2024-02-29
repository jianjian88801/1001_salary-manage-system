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

        <div class="layui-form layuimini-form" lay-filter="infoForm">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="trueName" class="layui-input" readonly >
                </div>
                <label class="layui-form-label">工号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="workId" value="" class="layui-input" readonly>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="times" value="" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">底薪：</label>
                <div class="layui-input-inline">
                    <input type="text" name="baseSalary" value="" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">迟到罚金：</label>
                <div class="layui-input-inline">
                    <input type="text" name="late" value="" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">早退罚金：</label>
                <div class="layui-input-inline">
                    <input type="text" name="early" value="" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">请假罚金：</label>
                <div class="layui-input-inline">
                    <input type="text" name="leave" value="" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">加班奖金：</label>
                <div class="layui-input-inline">
                    <input type="text" name="overtimes" value="" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">养老保险：</label>
                <div class="layui-input-inline">
                    <input type="text" name="endowment" value="" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">失业保险：</label>
                <div class="layui-input-inline">
                    <input type="text" name="unemployment" value="" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">工伤保险：</label>
                <div class="layui-input-inline">
                    <input type="text" name="injury" value="" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">生育保险：</label>
                <div class="layui-input-inline">
                    <input type="text" name="maternity" value="" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">医疗保险：</label>
                <div class="layui-input-inline">
                    <input type="text" name="medical" value="" class="layui-input" readonly>
                </div>
                <label class="layui-form-label">公积金：</label>
                <div class="layui-input-inline">
                    <input type="text" name="accumulation" value="" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">实发工资：</label>
                <div class="layui-input-inline">
                    <input type="text" name="finalSalary" value="" class="layui-input" readonly>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="../../layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../../layui/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    function child (data){
        console.log(data)
        layui.use(['form', 'miniTab'], function () {
            let form = layui.form,
                layer = layui.layer,
                miniTab = layui.miniTab;

            form.val("infoForm", {
                "trueName": data.trueName// "name": "value"
                ,"workId": data.workId
                ,"times": data.times
                ,"baseSalary": data.baseSalary
                ,"late": data.late
                ,"early": data.early
                ,"leave": data.leave
                ,"overtimes": data.overtimes
                ,"endowment": data.endowment
                ,"unemployment": data.unemployment
                ,"injury": data.injury
                ,"maternity": data.maternity
                ,"medical": data.medical
                ,"accumulation": data.accumulation
                ,"finalSalary": data.finalSalary
            });

        });
    }

</script>
</body>
</html>
