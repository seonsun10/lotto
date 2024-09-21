package com.sw.lotto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.lotto.dto.LottoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LottoController {

    ObjectMapper objectMapper = new ObjectMapper();

    // 로또 회차
    private static int count = 1;
    // 로또 정보 리스트
    private List<LottoDTO> lottoList = new ArrayList<>();

    @RequestMapping({"","/"})
    public String main(){

        return "lottoMain";
    }

    /**
     * 로또 회차별 정보 조회
     * @param drwNo
     * @return
     */
    @ResponseBody
    @GetMapping("/getLottoData")
    public LottoDTO getLottoData(@RequestParam String drwNo) {
        LottoDTO lottoDTO = null;
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+drwNo;

        // 외부 API 호출
        ResponseEntity<String> result = restTemplate.getForEntity(apiUrl, String.class);

        try {
            // objectMapper를 이용하여 String을 DTO로 변환
            lottoDTO = objectMapper.readValue(result.getBody(), LottoDTO.class);
        }catch(Exception e){
            e.printStackTrace();
        }

        // 응답 반환
        return lottoDTO;
    }

    /**
     * DB에 저장하기 위한 로또 정보 조회 스케줄
     */
    @Scheduled(fixedRate = 5000)
    public void lottoSchduled(){
        System.out.println("현재 회차 : " + count);
        LottoDTO lottoDTO = this.getLottoData(String.valueOf(count));
        lottoList.add(lottoDTO);

        // 10개마다 DB에 저장
        if(lottoList.size() % 10 == 0 ){

        }
        count++;
    }
}
