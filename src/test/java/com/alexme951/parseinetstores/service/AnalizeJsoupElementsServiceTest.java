package com.alexme951.parseinetstores.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Test;

public class AnalizeJsoupElementsServiceTest {

  private AnalizeJsoupElementsService service = new AnalizeJsoupElementsService();

  @Test
  public void extractProductUrls_SingleElement() {
    // given
    Tag tag = Tag.valueOf("href");
    Element element = new Element(tag, "\\product\\testProduct");
    Elements elements = new Elements();
    elements.add(element);
    // when
    List<String> result = service.extractProductUrls(elements);
    // then
    assertEquals(1, result.size());
    assertEquals("\\product\\testProduct", result.get(0));
  }
}