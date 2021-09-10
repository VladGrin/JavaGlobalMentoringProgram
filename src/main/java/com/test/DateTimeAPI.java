package com.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeAPI {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        String thatSpecialDay = now.withDayOfMonth(1)
                .atZone(ZoneId.of("Europe/Paris"))
                .plus(Duration.ofDays(5))
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println(thatSpecialDay);
    }
}
