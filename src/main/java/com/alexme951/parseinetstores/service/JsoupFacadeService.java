package com.alexme951.parseinetstores.service;

import static com.alexme951.parseinetstores.ProjectConstants.PARSING_TIMEOUT_MS;

import java.net.URL;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JsoupFacadeService {

  @SneakyThrows
  public Elements parsePage(String url) {
    Document result = Jsoup.parse(new URL(url), PARSING_TIMEOUT_MS);
    return result.body().getAllElements();
  }

}
