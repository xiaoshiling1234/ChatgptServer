package com.ck.ai.schedule;

import com.ck.ai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FreshFreeChatTimes {
    @Autowired
    UserMapper userMapper;
    @Scheduled(cron = "0 0 17 * * ?") // 每 5 秒执行一次
    public void doTask() {
        userMapper.updateFreeChatTime();
    }
}
