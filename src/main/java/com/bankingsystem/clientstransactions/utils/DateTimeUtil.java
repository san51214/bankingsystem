package com.bankingsystem.clientstransactions.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtil {

    public static long toMilliSeconds(LocalDateTime localDateTime) {
        return toDate(localDateTime).getTime();
    }

    public static Date getAddUnits(Date date, ChronoUnit temporalUnit, long unit) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        localDateTime = localDateTime.plus(unit, temporalUnit);

        Date dt = toDate(localDateTime);
        return dt;
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getNow() {
        return toDate(LocalDateTime.now());
    }

    public static LocalDateTime toLocalDateTime(long millis) {
        LocalDateTime date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }

    public static Date getDate(long millis) {
        return toDate(toLocalDateTime(millis));
    }

}
