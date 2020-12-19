package com.alexme951.parseinetstores.service.parsing.impl;

import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_DEFAULT_CATEGORY_URL;
import static com.alexme951.parseinetstores.ProjectConstants.AUCHAN_LINK_PREFIX;
import static com.alexme951.parseinetstores.ProjectConstants.CATALOG_LINKS_SELECT_QUERY;
import static com.alexme951.parseinetstores.utils.JsoupElementsUtils.getUrlsFromHrefs;

import com.alexme951.parseinetstores.old.AnalizeJsoupElementsService;
import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import com.alexme951.parseinetstores.repository.dto.parsing.ParsingHistoryEntryDto;
import com.alexme951.parseinetstores.repository.parsing.CategoryLinkRepository;
import com.alexme951.parseinetstores.service.dto.CategoryLink;
import com.alexme951.parseinetstores.service.jsoup.impl.JsoupFacadeServiceImpl;
import com.alexme951.parseinetstores.service.parsing.ParseCategoryLinksService;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ParseCategoryLinksServiceImpl implements ParseCategoryLinksService {

  private final CategoryLinkRepository repository;
  private final JsoupFacadeServiceImpl jsoup;
  private final AnalizeJsoupElementsService analizeJsoupElementsService;

  @Override
  public List<CategoryLink> parseAllCatalogLinks() {

    Elements allElements = jsoup.parsePage(AUCHAN_LINK_PREFIX);
    Elements categoryLinkElements = allElements.select(CATALOG_LINKS_SELECT_QUERY);
    List<String> categoryLinkUrls = getUrlsFromHrefs(categoryLinkElements);
    List<CategoryLink> result = mapToCategoryLinks(categoryLinkUrls);
    saveLinks(result);
    return result;
  }

  @Override
  public List<CategoryLink> parseSubCategoryLinks(String category) {
    return null;
  }

  private Set<CategoryLinkParsingDto> mapToCatalogLinksDto(List<CategoryLink> catalogLinkUrls) {
    Set<CategoryLinkParsingDto> categoryLinkParsingDtos =
     catalogLinkUrls.stream()
        .map(categoryLink -> {
          return new CategoryLinkParsingDto(
              new ParsingHistoryEntryDto(categoryLink.getParsingTime()), categoryLink.getLinkUrl(),
              null);
        })
        .collect(Collectors.toSet());

    for (CategoryLinkParsingDto categoryLink : categoryLinkParsingDtos) {
      String str1 = categoryLink.getLinkUrl().replace("/catalog/", "");
      for (CategoryLinkParsingDto link : categoryLinkParsingDtos) {
        String str2 = link.getLinkUrl().replace("/catalog/", "");
        String[] str3 = str2.split("/");
        String str4 = "";
        for (int i = 0; i < str3.length - 1; i++) {
          str4 += str3[i] + "/";
        }
        if (str4.equals(str1) && !str2.equals(str1)) {
          link.setParentCategoryLink(categoryLink);
        }
      }
    }

    return categoryLinkParsingDtos;
  }

  private List<CategoryLink> mapToCategoryLinks(List<String> categoryLinkUrls) {
    OffsetDateTime parsingTime = OffsetDateTime.now();
    List<CategoryLink> categoryLinks = categoryLinkUrls.stream()
        .map(url -> new CategoryLink(url, parsingTime))
        .collect(Collectors.toList());
    // To-Do:   Зафигачим метод определяющий связи

    for (CategoryLink categoryLink : categoryLinks) {
      String str1 = categoryLink.getLinkUrl().replace("/catalog/", "");
      for (CategoryLink link : categoryLinks) {
        String str2 = link.getLinkUrl().replace("/catalog/", "");
        String[] str3 = str2.split("/");
        String str4 = "";
        for (int i = 0; i < str3.length - 1; i++) {
          str4 += str3[i] + "/";
        }
        if (str4.equals(str1) && !str2.equals(str1)) {
          link.setCategoryLink(categoryLink);
        }
      }
    }

    return categoryLinks;
  }

  @Transactional
  void saveLinks(List<CategoryLink> catalogLinkUrls) {
    log.debug("Started saving links");
    Set<CategoryLinkParsingDto> categoryLinkDtos = mapToCatalogLinksDto(catalogLinkUrls);
    repository.saveAll(categoryLinkDtos);
    log.debug("{} links was saved into DB", categoryLinkDtos.size());
  }

  public Iterable<CategoryLinkParsingDto> getAllSavedCatalogLinks() {
    return repository.findAll();
  }

}
