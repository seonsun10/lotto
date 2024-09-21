package com.sw.lotto.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class LottoDTO {

    private String returnValue;     //요청결과
    private String drwNoDate;       //날짜
    private Long totSellamnt;       //총상금액
    private Long firstAccumamnt;    //총당첨금
    private Long firstPrzwnerCo;    //1등 당첨인원
    private Long firstWinamnt;      //1등 당첨금
    private int drwNo;             //로또회차
    private int bnusNo;            //보너스번호
    private int drwtNo1;            //번호1
    private int drwtNo2;            //번호2
    private int drwtNo3;            //번호3
    private int drwtNo4;            //번호4
    private int drwtNo5;            //번호5
    private int drwtNo6;            //번호6

}
