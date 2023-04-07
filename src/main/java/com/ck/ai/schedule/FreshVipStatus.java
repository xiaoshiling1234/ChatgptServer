package com.ck.ai.schedule;

import com.ck.ai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 每天刷新Vip状态
 */
@Component
public class FreshVipStatus {
    @Autowired
    UserMapper userMapper;
    @Scheduled(cron = "0 */1 * * *  ?") // cron 表达式，每隔1分钟执行一次
    public void doTask() {
        userMapper.updateVipStatus();
    }
}
