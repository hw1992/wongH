package com.wong.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*总共七位，分别表示
    秒（0-59），分（0-59），时（0-23），日期 天/日（1-31），月份）（1-12），星期（1-7,1表示星晴天，7表示星期六），年（可以缺省。取值范围是1970-2099）。
    除此之外，还有一些特殊符号：
    1） *   号，表示每，如果用*号占位，则表示每秒每分每时每天每月每年···这些   例如 0 30 8 * * ？  每天8:30执行
    2） ？  问号，只能出现在日期和星期这两个位置，表示这个位置的值不确定，例如上个例子每天8:30点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。如果给定一个确定的值，那么日期和星期就可能会出现矛盾。
    3） -   减号，表示范围 例如12-15 可以表示12,13,14,15点执行
    4） ，  逗号，表示一个列表值，例如星期取值在1,3,5 就是在星期一三五执行
    5） /   斜杠，例如0/1 开始值为0   步长为1        0/1 每分钟执行一次*/

    @Scheduled(cron="0 0/1 * * * ?") //每分钟执行一次
    public void statusCheck() {
        //logger.info("每分钟执行一次  开始…");
        //statusTask.healthCheck();
        //logger.info("每分钟执行一次  结束。");
    }

    @Scheduled(fixedRate=20000)
    public void testTasks() {
        //logger.info("每20秒执行一次  开始…");
        //statusTask.healthCheck();
        //logger.info("每20秒执行一次  结束。");
    }
}
