package com.sccon.exercicio.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class AgeService {

    @Value("${exercicio.date.current.day}")
    private Integer currentDay;
    @Value("${exercicio.date.current.month}")
    private Integer currentMonth;
    @Value("${exercicio.date.current.year}")
    private Integer currentYear;

    public Long getElapsedTimeInDays(LocalDate date){
        return getElapsedTimeInChronoUnit(ChronoUnit.DAYS, date, getCurrentDate());
    }

    public Long getElapsedTimeInMonths(LocalDate date){
        return getElapsedTimeInChronoUnit(ChronoUnit.MONTHS, date, getCurrentDate());
    }

    public Long getElapsedTimeInYears(LocalDate date){
        return getElapsedTimeInChronoUnit(ChronoUnit.YEARS, date, getCurrentDate());
    }

    public Long getElapsedTimeInChronoUnit(ChronoUnit chronoUnit, LocalDate date1, LocalDate date2){
        return chronoUnit.between(date1, date2);
    }

    public LocalDate getCurrentDate(){
        try{
            return LocalDate.of(currentYear, currentMonth, currentDay);
        }catch (DateTimeException e){
            log.error("Invalid parameters for current date, returning default value");
            return LocalDate.now();
        }
    }
}
