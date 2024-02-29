package com.lsnu.sms.controller;

import com.lsnu.sms.model.Page;
import com.lsnu.sms.model.Staff;
import com.lsnu.sms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    /**
     * 员工管理页
     */
    @GetMapping("/list")
    public ModelAndView dept (ModelAndView model){
        model.setViewName("staff/staff_list");
        return model;
    }
    /**
     * 获取员工列表
     */
    @GetMapping("/get_list")
    @ResponseBody
    public Map<String,Object> getList(
            @RequestParam(value = "workId",required = false,defaultValue = "") String workId,
            @RequestParam(value = "trueName",required = false,defaultValue = "") String trueName,
            @RequestParam(value = "sex",required = false,defaultValue = "") String sex,
            @RequestParam(value = "timeMin",required = false,defaultValue = "") String timeMin,
            @RequestParam(value = "timeMax",required = false,defaultValue = "") String timeMax,
            Page page
    ){
        if (page.getRows() == 0) page.setRows(10);
        Map<String,Object> ret = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("workId",workId);
        queryMap.put("trueName",'%'+trueName+'%');
        queryMap.put("sex",sex);
        queryMap.put("timeMin",timeMin);
        queryMap.put("timeMax",timeMax);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", staffService.getList(queryMap));
        ret.put("total", staffService.getTotal(queryMap));
        return ret;
    }
    /**
     * 添加员工页
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView model){
        model.setViewName("staff/staff_add");
        return model;
    }
    /**
     * 添加员工信息
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String,String> add (Staff staff){
        Map<String,String> ret = new HashMap<>();
        if(staffService.findById(staff.getWorkId()) != null){
            ret.put("type","error");
            ret.put("msg","该员工工号已存在！");
            return ret;
        }
        if(staffService.add(staff) <=0 ){
            ret.put("type","error");
            ret.put("msg","添加失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","添加成功！请前去设置基本工资");
        return ret;
    }
    /**
     * 修改员工页
     */
    @GetMapping("/edit")
    public ModelAndView edit(ModelAndView model){
        model.setViewName("staff/staff_edit");
        return model;
    }
    /**
     * 修改员工信息
     */
    @PostMapping("/edit")
    @ResponseBody
    public Map<String,String> edit (Staff staff){
        Map<String,String> ret = new HashMap<>();
        if(staffService.edit(staff) <=0 ){
            ret.put("type","error");
            ret.put("msg","修改失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }
    /**
     * 删除员工信息
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,String> delete (Staff staff){
        Map<String,String> ret = new HashMap<>();
        if(staffService.delete(staff.getWorkId()) <=0 ){
            ret.put("type","error");
            ret.put("msg","删除失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","删除成功！");
        return ret;
    }
}
