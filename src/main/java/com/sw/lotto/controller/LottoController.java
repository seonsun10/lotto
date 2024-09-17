package com.sw.lotto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.lotto.dto.LottoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class LottoController {

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping({"","/"})
    public String main(){

        return "lottoMain";
    }

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
}
