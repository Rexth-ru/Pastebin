package com.example.pastebin.en;

import lombok.Data;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public enum Time {
    NO_LIMITS(Long.MAX_VALUE, ChronoUnit.FOREVER), ONE_HOUR(1L, ChronoUnit.HOURS),
    ONE_MONTH(30L, ChronoUnit.DAYS), ONE_WEEK(7L, ChronoUnit.DAYS),
    TEN_MINUTE(10L, ChronoUnit.MINUTES), THREE_HOUR(3L, ChronoUnit.HOURS);
    private final Long time;
    private final ChronoUnit chronoUnit;

    Time(Long time, ChronoUnit chronoUnit) {
        this.time = time;
        this.chronoUnit = chronoUnit;
    }
    public Long getTime(){
        return  time;
    }
    public ChronoUnit getChronoUnit(){
        return chronoUnit;
    }
}