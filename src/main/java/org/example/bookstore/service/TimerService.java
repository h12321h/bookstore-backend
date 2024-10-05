package org.example.bookstore.service;

import java.time.Duration;

public interface TimerService {
    // 开始计时
    void startTimer();

    // 停止计时并返回会话时间
    Duration stopTimer();
}
