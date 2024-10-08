package com.sw.lotto.repository;

import com.sw.lotto.dto.LottoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface LottoMapper {

    List<LottoDTO> selectLottoNum();

    void insertLottoNum(LottoDTO lotto);
}
