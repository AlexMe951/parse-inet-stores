package com.alexme951.parseinetstores.service.parsing.impl;

import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.jsoup.JsoupFacadeService;
import com.alexme951.parseinetstores.service.parsing.ExtractProductInfoService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtractProductInfoServiceImpl implements ExtractProductInfoService {

  private final JsoupFacadeService jsoup;

  @Override
  public Product extractInfo(ProductLink productLink) {

    String productDescription = "";
    Map<String, String> productAttributes = new HashMap<>();
    Document document = jsoup.parsePageToDocument(productLink.linkUrl());
    Elements el = document.getAllElements();

    Elements elBodyTableTr = document.getElementsByTag("tr");
    Elements elH3 = document.getElementsByTag("h3");
    for (Element element : elH3) {
      if ("Описание".equals(element.ownText())) {
        Element description = element;
        Iterator childIter = description.parentNode().childNodes().iterator();

        while (childIter.hasNext()) {
          Node node = (Node) childIter.next();
          if ("Описание".equals(((Element) node).ownText())) {
            node = (Node) childIter.next();
            node = (Node) childIter.next();
            productDescription = ((Element) node).ownText();
            break;
          }
        }
        break;
      }
    }

    for (Element element : elBodyTableTr) {
      Elements elTh = element.getElementsByTag("th");
      Elements elTd = element.getElementsByTag("td");
      if (elTh.size() > 0 && elTd.size() > 0) {
        productAttributes.put(elTh.get(0).ownText(), elTd.get(0).ownText());
      }
    }

    Elements elements = document.getElementsByAttributeValue("id", "productName");
    String productName = elements.size() > 0 ? elements.get(0).ownText() : "NoName";

    String productCode = productAttributes.get("Артикул товара");
    productAttributes.remove("Артикул товара");

    return new Product(productName, productDescription, productCode, productAttributes);
  }
}
