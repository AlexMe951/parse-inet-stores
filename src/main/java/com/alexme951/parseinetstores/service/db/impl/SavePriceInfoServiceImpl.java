package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.service.db.SavePriceInfoService;
import com.alexme951.parseinetstores.service.dto.PriceInfo;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavePriceInfoServiceImpl implements SavePriceInfoService {

  private final MapperFacade mapperFacade;

  @Override
  public void savePriceInfo(List<PriceInfo> priceInfoList) {

  }

  @Override
  public void savePriceInfo(List<PriceInfo> priceInfoList, OffsetDateTime parsingTime) {

  }

  @Override
  public void savePriceInfo(PriceInfo priceInfo, OffsetDateTime parsingTime) {

  }
}
