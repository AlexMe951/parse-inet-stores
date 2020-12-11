package com.alexme951.parseinetstores.utils;

import static com.alexme951.parseinetstores.ProjectConstants.HREF_HTML_ATTR_KEY;

import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.jsoup.select.Elements;

@UtilityClass
public class JsoupElementsUtils {
  public static List<String> getUrlsFromHrefs(Elements hrefElements) {
    return hrefElements
        .stream()
        .map(elm -> elm.attributes().get(HREF_HTML_ATTR_KEY))
        .collect(Collectors.toList());
  }
}
