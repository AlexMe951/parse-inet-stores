package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.service.dto.PriceInfo;
import java.time.OffsetDateTime;
import java.util.List;

public interface SavePriceInfoService {

  void savePriceInfo(List<PriceInfo> priceInfoList);

  void savePriceInfo(List<PriceInfo> priceInfoList, OffsetDateTime parsingTime);

  void savePriceInfo(PriceInfo priceInfo, OffsetDateTime parsingTime);
}
