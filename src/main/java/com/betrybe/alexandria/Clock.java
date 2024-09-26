package com.betrybe.alexandria;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 8, 19, 23, 10, 40, 0);
        LocalDateTime currentDateTime2 = LocalDateTime.of(2023, 8, 19, 23, 10, 40, 0);

        boolean isBefore = currentDateTime2.isBefore(currentDateTime);
        boolean isBefore2 = currentDateTime.isBefore(currentDateTime2);
        boolean isAfter = currentDateTime.isAfter(currentDateTime2);

        System.out.println("aqui é pra dar true:");
        System.out.println(isBefore);
        System.out.println("aqui é pra dar false");
        System.out.println(isBefore2);
        System.out.println("aqui é pra dar true");
        System.out.println(isAfter);

        LocalDate dataLocal = LocalDate.now();

        System.out.println("Data local agora:");
        System.out.println(dataLocal);

    }
}
