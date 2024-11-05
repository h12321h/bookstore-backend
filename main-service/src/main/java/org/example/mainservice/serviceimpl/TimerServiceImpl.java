package org.example.mainservice.serviceimpl;

import org.example.mainservice.service.TimerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Scope("session") // 每个用户的会话拥有一个独立的 TimerService 实例
public class TimerServiceImpl implements TimerService {

    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

    // 开始计时
    @Override
    public void startTimer() {
        this.loginTime = LocalDateTime.now();
    }

    // 停止计时并返回会话时间
    @Override
    public Duration stopTimer() {
        this.logoutTime = LocalDateTime.now();
        return Duration.between(loginTime, logoutTime); // 计算会话持续时间
    }
}
