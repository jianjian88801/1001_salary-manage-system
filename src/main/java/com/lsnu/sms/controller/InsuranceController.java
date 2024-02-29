package com.lsnu.sms.controller;

import com.lsnu.sms.model.Insurance;
import com.lsnu.sms.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @GetMapping("/insurances")
    public ModelAndView page(ModelAndView model){
        Insurance insurance = insuranceService.getList();
        model.setViewName("salary/insurance");
        model.addObject("data",insurance);
        return model;
    }
    /**
     * 修改保险设置
     */
    @PostMapping("/edit")
    @ResponseBody
    public Map<String,Object> edit(Insurance insurance){
        Map<String,Object> ret = new HashMap<>();
        if (insuranceService.edit(insurance)<=0){
            ret.put("type","error");
            ret.put("msg","修改失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }
}
