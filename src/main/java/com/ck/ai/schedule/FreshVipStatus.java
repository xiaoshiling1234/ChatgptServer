package com.ck.ai.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * todo:每天刷新Vip状态
 */
@Component
public class FreshVipStatus {
    @Scheduled(fixedDelay = 5000) // 每 5 秒执行一次
    public void doTask() {
        // 执行定时任务的逻辑
    }
}
