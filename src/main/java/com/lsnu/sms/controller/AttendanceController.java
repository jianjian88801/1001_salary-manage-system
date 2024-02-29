package com.lsnu.sms.controller;

import com.lsnu.sms.model.Attendance;
import com.lsnu.sms.model.AttendanceSet;
import com.lsnu.sms.model.Page;
import com.lsnu.sms.service.AttendanceService;
import com.lsnu.sms.service.AttendanceSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceSetService attendanceSetService;
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/set")
    public ModelAndView setPage(ModelAndView model) {
        AttendanceSet attendanceSet = attendanceSetService.getList();
        model.setViewName("attendance/setting");
        model.addObject("data", attendanceSet);
        return model;
    }

    /**
     * 修改考勤奖惩
     */
    @PostMapping("/edit_set")
    @ResponseBody
    public Map<String, Object> edit(AttendanceSet attendanceSet) {
        Map<String, Object> ret = new HashMap<>();
        if (attendanceSetService.edit(attendanceSet) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "修改失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功！");
        return ret;
    }

    /**
     * 考勤列表
     */
    @GetMapping("/list")
    public ModelAndView list(ModelAndView model) {
        model.setViewName("attendance/attendance_list");
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
        int res = attendanceService.add(queryMap);
        if (res > 0) {
            queryMap.replace("workId","");
            ret.put("rows", attendanceService.getList(queryMap));
            ret.put("total", attendanceService.getTotal(queryMap));
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
        if (page.getRows() == 0) page.setRows(10);
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("workId", workId);
        queryMap.put("trueName", "%" + trueName + "%");
        queryMap.put("times", times);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", attendanceService.getList(queryMap));
        ret.put("total", attendanceService.getTotal(queryMap));
        return ret;
    }
    /**
     * 修改
     */
    @PostMapping("/edit")
    @ResponseBody
    public Map<String,Object> edit(Attendance attendance){
        Map<String,Object> ret = new HashMap<>();
        if(attendanceService.edit(attendance) <=0 ){
            ret.put("type","error");
            ret.put("msg","修改失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }
}
