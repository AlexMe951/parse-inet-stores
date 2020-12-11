package com.alexme951.parseinetstores.old;

import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_CATALOG_LINK_PREFIX;
import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_DEFAULT_CATEGORY_URL;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_LINKS_SELECT_QUERY;
import static com.alexme951.parseinetstores.utils.JsoupElementsUtils.getUrlsFromHrefs;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.repository.parsing.CategoryLinkRepository;
import com.alexme951.parseinetstores.service.jsoup.impl.JsoupFacadeServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuchanParseStoreService {

  private final CategoryLinkRepository repository;
  private final JsoupFacadeServiceImpl jsoup;
  private final AnalizeJsoupElementsService analizeJsoupElementsService;

  @SneakyThrows
  public List<String> parseAllCatalogLinks() {
    Elements allElements = jsoup.parsePage(AUCHAN_DEFAULT_CATEGORY_URL);
    Elements catalogLinkElements = allElements.select(CATALOG_LINKS_SELECT_QUERY);
    List<String> catalogLinkUrls = getUrlsFromHrefs(catalogLinkElements);
    saveLinks(catalogLinkUrls);
    return catalogLinkUrls;
  }

  @SneakyThrows
  public List<String> parseAllCategoryCatalogLinks(String category) {
    String urlForParsing = formUrlForParsing(category);
    Elements allElements = jsoup.parsePage(urlForParsing);
    String selectQuery = createCategorySelectQuery(category);
    Elements categoryCatalogLinkElements = allElements.select(selectQuery);
    List<String> catalogLinkUrls = getUrlsFromHrefs(categoryCatalogLinkElements);
    saveLinks(catalogLinkUrls);
    return catalogLinkUrls;
  }

  private String formUrlForParsing(String category) {
    return (AUCHAN_CATALOG_LINK_PREFIX + category + "/").replace("//", "/")
        .replace("https:/", "https://");
  }

  private String createCategorySelectQuery(String category) {
    return String.format(CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE, category);
  }

  private Set<CategoryLinkParsingDto> mapToCatalogLinks(List<String> catalogLinkUrls) {
    LocalDateTime parsingTime = LocalDateTime.now();
    return catalogLinkUrls.stream()
        .map(url -> new CategoryLinkParsingDto())
        .collect(Collectors.toSet());
  }

  private void saveLinks(List<String> catalogLinkUrls) {
    log.debug("Started saving links");
    Set<CategoryLinkParsingDto> categoryLinkDtos = mapToCatalogLinks(catalogLinkUrls);
    repository.saveAll(categoryLinkDtos);
    log.debug("{} links was saved into DB", categoryLinkDtos.size());
  }

  public Iterable<CategoryLinkParsingDto> getAllSavedCatalogLinks() {
    return repository.findAll();
  }

  public List<String> parseAllProductLinks(String category) {
    String urlForParsing = formUrlForParsing(category);
    Elements allElements = jsoup.parsePage(urlForParsing);
    return analizeJsoupElementsService.extractProductUrls(allElements);
  }

  public List<String> parseAllProductLinks() {
    return parseAllCatalogLinks().stream()
        .map(link -> link.replace("/catalog/", ""))
        .flatMap(category -> parseAllProductLinks(category).stream())
        .distinct()
        .collect(Collectors.toList());
//    List<Elements> allElements = allCatalogLinks.stream()
//        .map(categoryLink -> jsoup.parsePage((AUCHAN_LINK_PREFIX + categoryLink).replace("//", "/")))
//        .collect(Collectors.toList());
//    return allElements
//        .stream()
//        .map(analizeJsoupElementsService::extractProductUrls)
//        .flatMap(Collection::stream)
//        .distinct()
//        .collect(Collectors.toList());
//  }
  }
}