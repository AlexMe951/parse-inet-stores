package com.alexme951.parseinetstores.service;

import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_CATALOG_LINK_PREFIX;
import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_DEFAULT_CATEGORY_URL;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_LINKS_SELECT_QUERY;
import static com.alexme951.parseinetstores.ProjectConstants.HREF_HTML_ATTR_KEY;
import static com.alexme951.parseinetstores.ProjectConstants.PARSING_TIMEOUT_MS;

import com.alexme951.parseinetstores.repository.CatalogLinkRepository;
import com.alexme951.parseinetstores.repository.dto.CatalogLink;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuchanParseStoreCategoryService {

  private final CatalogLinkRepository repository;

  @SneakyThrows
  @NonNull
  public List<String> parseAllCatalogLinks() {
    Elements allElements = parsePage(AUCHAN_DEFAULT_CATEGORY_URL);
    Elements catalogLinkElements = allElements.select(CATALOG_LINKS_SELECT_QUERY);
    List<String> catalogLinkUrls = getUrlsFromHrefs(catalogLinkElements);
    saveLinks(catalogLinkUrls);
    return catalogLinkUrls;
  }

  @SneakyThrows
  @NonNull
  public List<String> parseAllCategoryCatalogLinks(@NonNull String category) {
    String urlForParsing = formUrlForParsing(category);
    Elements allElements = parsePage(urlForParsing);
    String selectQuery = createCategorySelectQuery(category);
    Elements categoryCatalogLinkElements = allElements.select(selectQuery);
    List<String> catalogLinkUrls = getUrlsFromHrefs(categoryCatalogLinkElements);
    saveLinks(catalogLinkUrls);
    return catalogLinkUrls;
  }

  @NotNull
  private String formUrlForParsing(@NonNull String category) {
    return AUCHAN_CATALOG_LINK_PREFIX + category + "/";
  }

  @SneakyThrows
  @NonNull
  private Elements parsePage(@NonNull String url) {
    Document result = Jsoup.parse(new URL(url), PARSING_TIMEOUT_MS);
    return result.body().getAllElements();
  }

  @NonNull
  private String createCategorySelectQuery(@NonNull String category) {
    return String.format(CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE, category);
  }

  @NonNull
  private List<String> getUrlsFromHrefs(@NonNull Elements hrefElements) {
    return hrefElements
        .stream()
        .map(elm -> elm.attributes().get(HREF_HTML_ATTR_KEY))
        .collect(Collectors.toList());
  }

  @NonNull
  private Set<CatalogLink> mapToCatalogLinks(@NonNull List<String> catalogLinkUrls) {
    LocalDateTime parsingTime = LocalDateTime.now();
    return catalogLinkUrls.stream()
        .map(url -> new CatalogLink(null, parsingTime, url))
        .collect(Collectors.toSet());
  }

  private void saveLinks(@NonNull List<String> catalogLinkUrls) {
    log.debug("Started saving links");
    Set<CatalogLink> catalogLinks = mapToCatalogLinks(catalogLinkUrls);
    repository.saveAll(catalogLinks);
    log.debug("{} links was saved into DB", catalogLinks.size());
  }

  public @NonNull
  Iterable<CatalogLink> getAllSavedCatalogLinks() {
    return repository.findAll();
  }

  public @NonNull
  List<CatalogLink> findAllByLinkUrl(@NonNull String linkUrl) {
    List<CatalogLink> result = new ArrayList<CatalogLink>();
    Iterable<CatalogLink> catalogLinks = getAllSavedCatalogLinks();
    for (CatalogLink catalogLink : catalogLinks) {
      if (catalogLink.getLinkUrl().toLowerCase().contains(linkUrl.toLowerCase())) {
        result.add(catalogLink);
      }
    }
    return result;
  }

}