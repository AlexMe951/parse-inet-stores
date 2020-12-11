package com.alexme951.parseinetstores.old;

import static com.alexme951.parseinetstores.ProjectConstants.PRODUCT_LINKS_SELECT_QUERY;
import static com.alexme951.parseinetstores.utils.JsoupElementsUtils.getUrlsFromHrefs;

import java.util.List;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class AnalizeJsoupElementsService {

  public List<String> extractProductUrls(Elements elements){
    Elements selectedElements = elements.select(PRODUCT_LINKS_SELECT_QUERY);
    return getUrlsFromHrefs(selectedElements);
  }
}
