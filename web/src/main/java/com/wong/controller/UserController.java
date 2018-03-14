package com.wong.controller;

import com.wong.common.JsonResult;
import com.wong.common.ResultCode;
import com.wong.entity.User;
import com.wong.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping(value="/regist")
    public ModelAndView regist(){
        ModelAndView mv = new ModelAndView("hello");
        return mv;
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(defaultValue = "") String mobile, @RequestParam(defaultValue = "")  String password,
                        ModelMap map, HttpSession session){
        ModelAndView mv = new ModelAndView("highcharts");
        User user = userService.login(Long.valueOf(mobile),password);
        if(user == null){
            ModelAndView mv1 = new ModelAndView("404");
            return mv1;
        }
        //session.setAttribute("loginUser",user);
        //session.setAttribute("authorities",user.getId());
        return mv;
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private JsonResult register(@RequestBody Map<String, Object> params) {
        if (params == null ||
                StringUtils.isEmpty(params.get("mobile").toString()) ||
                StringUtils.isEmpty(params.get("password").toString())) {
            return new JsonResult(ResultCode.PARAMS_ERROR,"参数错误");
        }
        Long mobile = Long.valueOf(params.get("mobile").toString());
        String password = params.get("password").toString();
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(password);
        int result = userService.insertSelective(user);
        if (result > 0) {
            User user1 = userService.selectByPrimaryKey(user.getId());
            return new JsonResult(ResultCode.SUCCESS,"成功",user1);
        }
        return new JsonResult(ResultCode.UNKNOWN_ERROR, "未知错误");
    }

    @RequestMapping(value = "/login1", method = RequestMethod.POST)
    private JsonResult login1(@RequestBody Map<String, Object> params) {
        if (params == null ||
                StringUtils.isEmpty(params.get("mobile").toString()) ||
                StringUtils.isEmpty(params.get("password").toString())) {
            return new JsonResult(ResultCode.PARAMS_ERROR,"参数错误");
        }
        Long mobile = Long.valueOf(params.get("mobile").toString());
        String password = params.get("password").toString();
        User user = userService.login(mobile,password);
        if (user != null) {
            return new JsonResult(ResultCode.SUCCESS,"成功",user);
        }
        return new JsonResult(ResultCode.UNKNOWN_ERROR, "未知错误");
    }
}