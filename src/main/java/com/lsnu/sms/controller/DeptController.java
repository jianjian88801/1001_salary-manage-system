package com.lsnu.sms.controller;

import com.lsnu.sms.model.Dept;
import com.lsnu.sms.model.Page;
import com.lsnu.sms.model.Staff;
import com.lsnu.sms.service.DeptService;
import com.lsnu.sms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private StaffService staffService;

    /**
     * 部门管理页
     */
    @GetMapping("/dept")
    public ModelAndView dept(ModelAndView model) {
        model.setViewName("dept/dept_list");
        return model;
    }

    /**
     * 获取部门列表
     */
    @GetMapping("/get_list")
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value = "deptName", required = false, defaultValue = "") String deptName,
            @RequestParam(value = "deptManager", required = false, defaultValue = "") String deptManager,
            Page page
    ) {
        if (page.getRows() == 0) page.setRows(10);
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("deptName", deptName);
        queryMap.put("deptManager", deptManager);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", deptService.getList(queryMap));
        ret.put("total", deptService.getTotal(queryMap));
        return ret;
    }

    /**
     * 添加部门页
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView model) {
        model.setViewName("dept/dept_add");
        return model;
    }

    /**
     * 添加部门信息
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String, String> add(Dept dept) {
        Map<String, String> ret = new HashMap<>();
        if (deptService.findById(dept.getDeptId()) != null) {
            ret.put("type", "error");
            ret.put("msg", "该部门已存在！");
            return ret;
        }
        if (deptService.add(dept) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功！");
        return ret;
    }

    /**
     * 修改部门页
     */
    @GetMapping("/edit")
    public ModelAndView edit(ModelAndView model) {
        model.setViewName("dept/dept_edit");
        return model;
    }

    /**
     * 修改部门信息
     */
    @PostMapping("/edit")
    @ResponseBody
    public Map<String, String> edit(Dept dept) {
        Map<String, String> ret = new HashMap<>();
        if (dept.getWorkId() != "") {
            Staff staff = staffService.findById(dept.getWorkId());
            if (!dept.getDeptId().equals(staff.getDeptId())) {
                ret.put("type", "error");
                ret.put("msg", "该员工不属于该部门，请重新选择负责人！");
                return ret;
            }
        }
        if (deptService.edit(dept) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "修改失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功！");
        return ret;
    }

    /**
     * 删除部门信息
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, String> delete(Dept dept) {
        Map<String, String> ret = new HashMap<>();
        List<Staff> list = staffService.findByDeptId(dept.getDeptId());
        if (list.size() != 0) {
            ret.put("type", "error");
            ret.put("msg", "该部门下存在员工信息，请勿冲动！！");
            return ret;
        }
        if (deptService.delete(dept.getDeptId()) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "删除失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功！");
        return ret;
    }

    @GetMapping("/get_dept")
    @ResponseBody
    public Map<String, Object> getDept() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("rows", deptService.getDept());
        return ret;
    }

    @GetMapping("get_staff")
    @ResponseBody
    public Map<String,Object> getStaff(@RequestParam("deptId") String deptId){
        Map<String, Object> ret = new HashMap<>();
        List<Staff> list = staffService.findByDeptId(deptId);
        ret.put("rows",list);
        return ret;
    }
}
