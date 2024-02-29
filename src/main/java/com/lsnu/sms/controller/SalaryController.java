package com.lsnu.sms.controller;

import com.lsnu.sms.model.Page;
import com.lsnu.sms.model.Salary;
import com.lsnu.sms.service.SalaryService;
import com.lsnu.sms.service.WagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/salary")
@Controller
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private WagesService wagesService;
    /**
     * 基本工资页
     */
    @GetMapping("/base")
    public ModelAndView basePage(ModelAndView model){
        model.setViewName("salary/base_salary");
        return model;
    }
    /**
     * 获取基本工资列表
     */
    @GetMapping("/get_base_list")
    @ResponseBody
    public Map<String,Object> getList(
            @RequestParam(value = "workId",required = false,defaultValue = "") String workId,
            @RequestParam(value = "trueName",required = false,defaultValue = "") String trueName,
            Page page
    ){
        if (page.getRows() == 0) page.setRows(10);
        Map<String,Object> ret = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("workId",workId);
        queryMap.put("trueName",'%'+trueName+'%');
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", salaryService.getList(queryMap));
        ret.put("total", salaryService.getTotal(queryMap));
        return ret;
    }
    /**
     * 修改基本工资
     */
    @PostMapping("/edit_base")
    @ResponseBody
    public Map<String,String> edit (Salary salary){
        Map<String,String> ret = new HashMap<>();
        if(salaryService.edit(salary) <=0 ){
            ret.put("type","error");
            ret.put("msg","修改失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }
    /**
     * 实发工资页
     */
    @GetMapping("/final")
    public ModelAndView salary(ModelAndView model){
        model.setViewName("salary/salary_list");
        return model;
    }
    /**
     * 生成列表
     */
    @GetMapping("/add")
    @ResponseBody
    public Map<String, Object> add(
            @RequestParam(value = "times") String times
            , Page page
    ) {
        if (page.getRows() == 0) page.setRows(10);
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("workId", "");
        queryMap.put("trueName", "%%");
        queryMap.put("times", times);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        int res = wagesService.create(queryMap);
        if (res > 0) {
            queryMap.replace("workId","");
            ret.put("rows", wagesService.getList(queryMap));
            ret.put("total", wagesService.getTotal(queryMap));
            return ret;
        }
        return ret;
    }
    /**
     * 搜索查询
     */
    @GetMapping("/get_list")
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value = "workId", required = false, defaultValue = "") String workId
            , @RequestParam(value = "trueName", required = false, defaultValue = "") String trueName
            , @RequestParam(value = "times", required = false, defaultValue = "") String times
            , Page page
    ) {
        if (page.getPage() == 0) page.setPage(1);
        if (page.getRows() == 0) page.setRows(10);
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("workId", workId);
        queryMap.put("trueName", "%" + trueName + "%");
        queryMap.put("times", times);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", wagesService.getList(queryMap));
        ret.put("total", wagesService.getTotal(queryMap));
        return ret;
    }
    /**
     * 工资详细页面展示
     */
    @GetMapping("/info")
    public ModelAndView infoPage(ModelAndView model){
        model.setViewName("salary/salary_info");
        return model;
    }
}
