package com.sw.lotto.service;

import com.sw.lotto.dto.LottoDTO;
import com.sw.lotto.repository.LottoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LottoService {
    private final LottoMapper lottoMapper;

    public void insertLottoNum(List<LottoDTO> lottoList) {
        lottoMapper.insertLottoNum(lottoList);
    }

}
