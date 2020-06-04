package com.conf;

import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.Subscribe;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {
    @Subscribe
    public void onMessageEvent(String event) {
        log.info("Subscribe message:{}", event);
    }
}