package com.wong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wong.service.UserLogService;
import com.wong.vo.dto.UserLogDTO;
import com.wong.vo.loginCntVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/userLog")
public class HighchartsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserLogService userLogService;

    @GetMapping(value="/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("highcharts");
        return mv;
    }

    @PostMapping(value="/detail" ,produces="application/json;charset=UTF-8")
    public String detail(){
        List<UserLogDTO> userLogDTOList = userLogService.userLogDTO();
        ObjectMapper mapper = new ObjectMapper();
        StringWriter w = new StringWriter();
        //Convert between List and JSON
        try {
            mapper.writeValue(w, userLogDTOList);//开始序列化
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logger.error("finally");
        }
        return w.toString(); //将json格式的字符串返回给前台
    }

    @PostMapping(value="/loginCnt" ,produces="application/json;charset=UTF-8")
    public String loginCnt(){
        List<loginCntVO> loginCntVOList = userLogService.loginCnt();
        ObjectMapper mapper = new ObjectMapper();
        StringWriter w = new StringWriter();
        //Convert between List and JSON
        try {
            mapper.writeValue(w, loginCntVOList);//开始序列化
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logger.error("finally");
        }
        return w.toString(); //将json格式的字符串返回给前台
    }
}