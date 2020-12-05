package com.alexme951.parseinetstores.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alexme951.parseinetstores.ParseInetStoresApplication;
import com.alexme951.parseinetstores.ParseInetStoresApplicationTests;
import com.alexme951.parseinetstores.TestSpringConfiguration;
import com.alexme951.parseinetstores.repository.CatalogLinkRepository;
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
import java.net.URL;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestSpringConfiguration.class, ParseInetStoresApplicationTests.class})
public class ExtractProductLinksServiceTest {

  private JsoupFacadeService jsoup = new JsoupFacadeService();

  private ExtractProductLinksService service_product = new ExtractProductLinksService(jsoup);

  @Test
  public void getAllProductLinksFromCatalogCategoryUrlsWithCategory() {
    Tag tag = Tag.valueOf("href");
    Element element = new Element(tag, "/product/pirozhnoe-biskvitnoe-7days-cake-bar-neglazirovannoe-s-kakao-30-g/");
    Elements elements = new Elements();
    elements.add(element);

    String urls = "https://www.auchan.ru/catalog/hlebnaya-vypechka/";
    //when(jsoup.parsePage(eq(urls))).thenReturn(elements);
    //when(jsoup.parsePage(eq("https://www.auchan.ru/catalog/hlebnaya-vypechka/"))).thenReturn(elements);

    List<String> result = service_product.getAllProductLinksFromCatalogCategoryUrls("/catalog/hlebnaya-vypechka/");

    assertEquals(100, result.size());
    assertEquals("/product/pirozhnoe-biskvitnoe-7days-cake-bar-neglazirovannoe-s-kakao-30-g/", result.get(0));
  }

  @Test
  public void getAllProductLinksFromCatalogCategoryUrlsWithNoneCategory() {
    Tag tag = Tag.valueOf("href");
    Element element = new Element(tag, "/product/pirozhnoe-biskvitnoe-7days-cake-bar-neglazirovannoe-s-kakao-30-g/");
    Elements elements = new Elements();
    elements.add(element);

    String urls = "https://www.auchan.ru/catalog/hlebnaya-vypechka/";
    //when(jsoup.parsePage(eq(urls))).thenReturn(elements);
    //when(jsoup.parsePage(eq("https://www.auchan.ru/catalog/hlebnaya-vypechka/"))).thenReturn(elements);

    List<String> result = service_product.getAllProductLinksFromCatalogCategoryUrls("/catalog/hlebnaya-vypechka/");

    assertEquals(100, result.size());
    assertNotEquals("/product/NoneCategory/", result.get(0));
  }

}