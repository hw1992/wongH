package com.wong.controller;

import com.wong.common.JsonResult;
import com.wong.common.ResultCode;
import com.wong.elasticsearch.service.BookInfoElasticsearchService;
import com.wong.entity.BookInfo;
import com.wong.entity.UserLog;
import com.wong.service.BookInfoService;
import com.wong.service.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookInfoElasticsearchService bookInfoElasticsearchService;

    @GetMapping(value="/model")
    public ModelAndView model(){
        UserLog userLog = userLogService.selectByPrimaryKey(125L);
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("time", new Date().toString());
        mv.addObject("userId", userLog.getUserId().toString());
        mv.addObject("operation", userLog.getOperation());
        mv.addObject("result", userLog.getResult());
        return mv;
    }

    @Transactional(isolation= Isolation.READ_COMMITTED)
    @PostMapping(value = "/demo")
    public JsonResult demo(@RequestBody Map<String, Object> params) {
        UserLog userLog =  new UserLog();
        Long id = 123L;
        userLog = userLogService.selectByPrimaryKey(id);
        if (userLog != null){
            logger.info("德玛西亚 " + userLog);
        }else {
            logger.error("德玛西亚没了");
        }
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("userLog", userLog);
        return new JsonResult(ResultCode.SUCCESS, "检测成功",map);
    }

    //Elasticsearch模糊查询
    @PostMapping(value = "/bookinfo")
    public JsonResult bookinfo(@RequestBody Map<String, Object> params) {
        //所有数据加到es中  没有单独写出来 懒得弄
        List<BookInfo> bookInfos = bookInfoService.getBookInfos();
        bookInfos.forEach(aaa -> bookInfoElasticsearchService.save(aaa));
        //这才是开始进行查询操作
        String keyword = params.get("keyword").toString();
        List<BookInfo> esbookInfos = bookInfoElasticsearchService.findBookInfoByTitleContainingOrAuthorContaining(keyword);
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("bookInfo", esbookInfos);
        return new JsonResult(ResultCode.SUCCESS, "检测成功",map);
    }

    //Elasticsearch删除
    @PostMapping("/delete")
    public String delete(){
        bookInfoElasticsearchService.delete(123);
        return "success";
    }

    //Elasticsearch更新
    @PostMapping("/update")
    public BookInfo update(){
        //es先保存得有数据
        //bookInfoElasticsearchService.save(bookInfoService.selectByPrimaryKey(123));
        BookInfo bookInfo = bookInfoElasticsearchService.findOne(123);
        bookInfo.setAuthor("huang");
        bookInfoElasticsearchService.save(bookInfo);
        return bookInfo;
    }
}