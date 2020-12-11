package com.alexme951.parseinetstores.service.jsoup;

import org.jsoup.select.Elements;

public interface JsoupFacadeService {
  Elements parsePage(String url);
}
