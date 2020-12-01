package com.alexme951.parseinetstores.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.alexme951.parseinetstores.ParseInetStoresApplication;
import com.alexme951.parseinetstores.TestSpringConfiguration;
import com.alexme951.parseinetstores.repository.CatalogLinkRepository;
import com.alexme951.parseinetstores.repository.dto.CatalogLink;
import com.google.common.collect.ImmutableList;
import java.time.LocalDateTime;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestSpringConfiguration.class, ParseInetStoresApplication.class})
public class AuchanParseStoreCategoryServiceTest {

  @Autowired
  private CatalogLinkRepository repository;
  @Autowired
  private JsoupFacadeService jsoup;
  @Autowired
  AuchanParseStoreCategoryService service;

  @Test
  public void parseAllCatalogLinks() {
    // given
    Tag tag = Tag.valueOf("href");
    Element element = new Element(tag, "/catalog/testCatalog");
    Elements elements = new Elements();
    elements.add(element);

    when(jsoup.parsePage(eq("https://www.auchan.ru/catalog/ptica-myaso/"))).thenReturn(elements);

    CatalogLink catalogLink = new CatalogLink(1L,
        LocalDateTime.of(1, 1, 1, 1, 1, 1, 1),
        "/catalog/testCatalog");

    // when
    List<String> result = service.parseAllCatalogLinks();

    // then
    assertEquals(1, result.size());
    assertEquals("/catalog/testCatalog", result.get(0));
    verify(repository).saveAll(eq(ImmutableList.of(catalogLink)));
    verifyNoMoreInteractions(repository);
  }
}