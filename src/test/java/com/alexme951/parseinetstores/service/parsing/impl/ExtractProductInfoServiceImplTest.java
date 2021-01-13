package com.alexme951.parseinetstores.service.parsing.impl;

import static org.junit.Assert.assertEquals;

import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.jsoup.JsoupFacadeService;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * Tests for {@link ExtractProductInfoServiceImpl}.
 * <p>
 */
public class ExtractProductInfoServiceImplTest {

  private final JsoupFacadeService jsoupFacadeService = new JsoupFacadeService() {
    @Override
    public Elements parsePage(String url) {
      try {
        //Document result = Jsoup.parse(productPageHtmlExample1);
        Document result = Jsoup.connect(productPageHtmlExample2.toLowerCase()).timeout(0)
            .userAgent("Opera").get();
        return result.body().getAllElements();
      } catch (Exception ex) {
        return new Elements();
      }
    }

    @Override
    public Document parsePageToDocument(String url) {
      try {
        //Document result = Jsoup.parse(new URL(url), PARSING_TIMEOUT_MS);
        Document result = Jsoup.connect(productPageHtmlExample2.toLowerCase()).timeout(0)
            .userAgent("Opera").get();
        return result;
      } catch (Exception ex) {
        return new Document(StringUtils.EMPTY);
      }
    }
  };

  private final ExtractProductInfoServiceImpl extractProductInfoService = new ExtractProductInfoServiceImpl(
      jsoupFacadeService);

  @Test
  public void extractInfo() {
//    given
    ProductLink productLink = new ProductLink(productPageHtmlExample2);
    Map<String, String> expectedAttributes = Map.of("Бренд", "Мираторг",
        "Калорийность", "230",
        "Белки на 100 г, г", "16",
        "Жиры на 100 г, г", "18",
        "Масса нетто, кг", "0.8",
        "Масса брутто, кг", "0.8",
        "Ингредиенты", "говядина",
        "ДxШxВ, мм", "180x150x60",
        "Производитель", "ООО «Брянская мясная компания»",
        "Страна производства", "Россия");
//    when
    Product product = extractProductInfoService.extractInfo(productLink);
//    then
    assertEquals("Name is not correct", "Мякоть бедра «Мираторг» говяжьего Ангус, 800 г",
        product.name());
    assertEquals("Description is not correct",
        "Бедро идеально подойдет для запекания, но чтобы сделать его по-настоящему нежным,"
            + " готовить мясо нужно медленно. Тогда вы получите удивительно вкусное блюдо с хрустящей корочкой и глубоким ароматом.",
        product.description());
    assertEquals("Code is not correct", "159729", product.code());
    assertEquals("Attributes size is not correct", expectedAttributes.size(),
        product.attributes().size());
    expectedAttributes.forEach((key, value) -> assertEquals("Attributes is not correct", value,
        product.attributes().get(key)));
  }

  private final static String productPageHtmlExample1 = "https://www.auchan.ru/product/karandash-konturnyy-dlya-glaz-i-gub-merilin-pink-rose-2-1-sht/";
  private final static String productPageHtmlExample2 = "https://www.auchan.ru/product/myakot-bedra-govyazhego-miratorg-angus-800-g/";

}