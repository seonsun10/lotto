package com.sw.lotto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.lotto.dto.LottoDTO;
import com.sw.lotto.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LottoController {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private LottoService lottoService;

    // 로또 회차
    private static int count = 1;
    // 로또 정보 리스트
    private List<LottoDTO> lottoList = new ArrayList<>();

    @RequestMapping({"","/"})
    public ModelAndView main(){
        ModelAndView mv = new ModelAndView();
        List<LottoDTO> resultList = this.selectLottoNum();
        System.out.println("resultList =====> " + resultList);

        mv.addObject("resultList", resultList);
        mv.setViewName("lottoMain");
//        this.lottoSchduled();
        return mv;
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
     * 로또 정보 조회
     * @return
     */
    public List<LottoDTO> selectLottoNum(){
        return lottoService.selectLottoNum();
    }

    /**
     * DB에 저장하기 위한 로또 정보 조회 스케줄
     */
//    @Scheduled(fixedRate = 5000)
    public void lottoSchduled(){
        int drwNo = 1135;
        LottoDTO lottoDTO = this.getLottoData(String.valueOf(drwNo));
        lottoList.add(lottoDTO);

        // 10개마다 DB에 저장
//        if(lottoList.size() % 10 == 0 ){
            System.out.println("현재 저장 회차 =====> " + lottoList.size());
            this.saveLottoData(lottoList);
//        }
//        count++;
    }

    /**
     * 로또 정보 저장
     * @param lottoList
     */
    @Transactional
    public void saveLottoData(List<LottoDTO> lottoList){
        // DB에 저장
        try {
            lottoService.insertLottoNum(lottoList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
