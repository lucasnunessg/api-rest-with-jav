package com.betrybe.alexandria;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class Clock {
    public static void main(String[] args) {
        //do local do sistema
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentDateNow = LocalDateTime.now();
        System.out.println("Horario abaixo é o atual");
        System.out.println(currentDateNow);
        System.out.println("Diferenciar");
        System.out.println(now);
     //do fuso proximo
       java.time.Clock relogio = java.time.Clock.systemDefaultZone();
       System.out.println(relogio.instant());
        LocalDateTime diaDoSofrimento = LocalDateTime.parse("2014-07-08T17:00:00");
        System.out.println(diaDoSofrimento);
    }



}
