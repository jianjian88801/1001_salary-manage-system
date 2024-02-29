package com.lsnu.sms.controller;

import com.alibaba.druid.util.StringUtils;
import com.lsnu.sms.model.User;
import com.lsnu.sms.service.UserService;
import com.lsnu.sms.utils.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/system")
@Controller
public class SystemController {
    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model) {
        model.setViewName("system/login");
        return model;
    }

    /**
     * 登录验证
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(
            @RequestParam(value = "workId") String workId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "captcha") String captcha,
            HttpServletRequest request) {
        Map<String, String> ret = new HashMap<>();
        User user = new User();
        user.setWorkId(workId);
        user.setPassword(password);
        if (userService.findByIdAndPassword(user) == null) {
            ret.put("type", "error");
            ret.put("msg", "用户名或密码错误，请重新输入！");
            return ret;
        }
        String loginCaptcha = (String) request.getSession().getAttribute("loginCaptcha");
        if (StringUtils.isEmpty(loginCaptcha)) {
            ret.put("type", "error");
            ret.put("msg", "长时间未操作，会话已失效，请刷新后重试!");
            return ret;
        }
        if (!captcha.toUpperCase().equals(loginCaptcha.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "验证码错误!");
            return ret;
        }
        request.getSession().setAttribute("loginCaptcha", null);
        request.getSession().setAttribute("user", user);
        ret.put("type", "success");
        ret.put("msg", "登录成功！");
        ret.put("data",workId);
        return ret;
    }
    /**
     * 显示 验证码
     */
    @RequestMapping(value = "/get_captcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        CpachaUtil cpachaUtil = new CpachaUtil(4, 110, 38);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("loginCaptcha", generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 主页面
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model) {
        model.setViewName("system/index");
        return model;
    }
    @GetMapping("/welcome")
    public ModelAndView welcome(ModelAndView model) {
        model.setViewName("system/welcome");
        return model;
    }
}
