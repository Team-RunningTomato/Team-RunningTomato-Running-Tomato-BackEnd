package com.tomato.running.domain.run.util;

import org.springframework.stereotype.Component;

@Component
public class LevelUtil {

    public Integer calculatePercentage(Integer totalDistance) {
        int nowLevel = totalDistance / 20;

        return (totalDistance - (nowLevel * 20)) * 5;
    }

    public Integer calculateLevel(Integer totalDistance) {
        return totalDistance / 20;
    }
}
