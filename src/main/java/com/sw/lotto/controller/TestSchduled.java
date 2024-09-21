package com.sw.lotto.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class TestSchduled {

    private static int count = 0;

    @Scheduled(cron= "5 0 0 * * *")
    public void schduledTest(){
        System.out.println("스케쥴러 테스트 : "+count);
        count++;
    }
}
