package com.alexme951.parseinetstores.service.jsoup.impl;

import static com.alexme951.parseinetstores.ProjectConstants.PARSING_TIMEOUT_MS;

import com.alexme951.parseinetstores.service.jsoup.JsoupFacadeService;
import java.net.URL;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsoupFacadeServiceImpl implements JsoupFacadeService {

  @SneakyThrows
  @Override
  public Elements parsePage(String url) {
    try {
      log.debug("Try to parse {}", url);
      //Document result = Jsoup.parse(new URL(url), PARSING_TIMEOUT_MS);
      Document result = Jsoup.connect(url.toLowerCase()).timeout(0).userAgent("Opera").get();
      Elements allElements = result.body().getAllElements();
      log.debug("Parsed {} elements", allElements.size());
      return allElements;
    } catch (Exception ex) {
      log.error("Caught exception while parsing {}", url);
      return new Elements();
    }
  }
}
