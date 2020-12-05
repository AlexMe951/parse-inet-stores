package com.alexme951.parseinetstores.service;

import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_CATALOG_LINK_PREFIX;
import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_DEFAULT_CATEGORY_URL;
import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_WWW_LINK_PREFIX;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_LINKS_SELECT_QUERY;
import static com.alexme951.parseinetstores.ProjectConstants.HREF_HTML_ATTR_KEY;
import static com.alexme951.parseinetstores.ProjectConstants.PRODUCT_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE;
import static com.alexme951.parseinetstores.ProjectConstants.PRODUCT_LINKS_SELECT_QUERY;

import com.alexme951.parseinetstores.repository.CatalogLinkRepository;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExtractProductLinksService {

  private final JsoupFacadeService jsoup;

  @SneakyThrows
  public List<String> getAllProductLinksFromCatalogCategoryUrls(String categoryLink) {
    String urls = AUCHAN_WWW_LINK_PREFIX+categoryLink;
    Elements allElements = jsoup.parsePage(urls);
    Elements productLinkElements = allElements.select(PRODUCT_LINKS_SELECT_QUERY);
    List<String> productLinkUrls = getUrlsFromHrefs(productLinkElements);
    return productLinkUrls;
  }

  private String formUrlForParsing(String category) {
    return AUCHAN_WWW_LINK_PREFIX + category + "/";
  }

  private String createProductSelectQuery(String category) {
    return String.format(PRODUCT_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE, category);
  }

  private List<String> getUrlsFromHrefs(Elements hrefElements) {
    return hrefElements
        .stream()
        .map(elm -> elm.attributes().get(HREF_HTML_ATTR_KEY))
        .collect(Collectors.toList());
  }


}
