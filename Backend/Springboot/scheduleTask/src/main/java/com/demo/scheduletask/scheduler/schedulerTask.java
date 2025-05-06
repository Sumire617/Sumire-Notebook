package com.demo.scheduletask.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

// 第一种写法, (cron = "0/5 * * * *?") 每5秒执行一次
@Component
class schedulerTask {
    private int count = 0;
    // 每5秒执行一次,0s不运行
    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduler() {
        count++;
        System.out.println("执行第" + count + "次");
    }
}
// 第二种写法
@Component
class schedulerTask2 {
    // 日期格式化器
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 每5秒执行一次,0s运行
    @Scheduled(fixedRate = 5000)
    public void scheduler2() {
        System.out.println("当前时间: " + sdf.format(System.currentTimeMillis()));
    }
}
