package com.alexme951.parseinetstores.service.parsing.impl;

import com.alexme951.parseinetstores.ProjectConstants;
import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.jsoup.JsoupFacadeService;
import com.alexme951.parseinetstores.service.parsing.ExtractProductInfoService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtractProductInfoServiceImpl implements ExtractProductInfoService {

  private final JsoupFacadeService jsoup;

  @Override
  public Product extractInfo(ProductLink productLink) {



    Map<String, String> productAttributes = new HashMap<>();
    Document document = jsoup.parsePageToDocument(productLink.linkUrl());
    Elements el = document.getAllElements();

    Elements elBodyTableTr = document.getElementsByTag("tr");

    for (Element element : elBodyTableTr) {
      Elements elTh = element.getElementsByTag("th");
      Elements elTd = element.getElementsByTag("td");
      productAttributes.put(elTh.get(0).ownText(),elTd.get(0).ownText());
    }


    Elements elements = document.getElementsByAttributeValue("id","productName");
    String productName = elements.get(0).ownText();

    String productDescription="";
    String productCode = productAttributes.get("Артикул товара");
    productAttributes.remove("Артикул товара");

    return new Product(productName,productDescription,productCode,productAttributes);
  }
}
