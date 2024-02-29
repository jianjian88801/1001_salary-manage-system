package com.lsnu.sms.controller;

import com.lsnu.sms.model.Staff;
import com.lsnu.sms.model.User;
import com.lsnu.sms.service.StaffService;
import com.lsnu.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private UserService userService;
    /**
     * 基本资料页面
     */
    @GetMapping("/base_info")
    public ModelAndView baseInfo(ModelAndView model,HttpServletRequest request) {
        model.setViewName("user/base_info");
        User user = (User)request.getSession().getAttribute("user");
        Staff staff = staffService.getInfo(user.getWorkId());
        model.addObject(staff);
        return model;
    }
    /**
    * 修改密码页面
    */
    @GetMapping("/user_password")
    public ModelAndView userPassword(ModelAndView model,HttpServletRequest request) {
        model.setViewName("user/user_password");
        User user = (User)request.getSession().getAttribute("user");
        model.addObject(user);
        return model;
    }
    /**
     * 提交基本信息
     */
    @PostMapping("/edit")
    @ResponseBody
    public Map<String, String> edit(Staff staff) {
        Map<String, String> ret = new HashMap<>();
        if(staffService.edit(staff)<=0){
            ret.put("type","error");
            ret.put("msg","修改失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }
    /**
     * 修改密码
     */
    @PostMapping("/edit_pwd")
    @ResponseBody
    public Map<String, String> edit_pwd(
            @RequestParam("workId") String workId,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("againPassword") String againPassword
    ) {
        Map<String, String> ret = new HashMap<>();
        if (newPassword.equals(oldPassword)){
            ret.put("type","error");
            ret.put("msg","新密码不能与原密码一致，请重新输入！");
            return ret;
        }
        if (!newPassword.equals(againPassword)){
            ret.put("type","error");
            ret.put("msg","新密码两次输入不一致，请重新输入！");
            return ret;
        }
        User user = new User();
        user.setWorkId(workId);
        user.setPassword(oldPassword);
        if (userService.findByIdAndPassword(user) == null){
            ret.put("type","error");
            ret.put("msg","原密码错误，请重新输入！");
            return ret;
        }
        user.setPassword(newPassword);
        if(userService.edit_pwd(user)<=0){
            ret.put("type","error");
            ret.put("msg","修改失败！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }
}
