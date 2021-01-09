package com.alexme951.parseinetstores.service.parsing.impl;

import com.alexme951.parseinetstores.ProjectConstants;
import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.jsoup.JsoupFacadeService;
import com.alexme951.parseinetstores.service.parsing.ExtractProductInfoService;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtractProductInfoServiceImpl implements ExtractProductInfoService {

  private final JsoupFacadeService jsoup;

  @Override
  public Product extractInfo(ProductLink productLink) {

    Document document = jsoup.parsePageToDocument(productLink.linkUrl());
    Elements el = document.getAllElements();
    Elements elements = document.getElementsByAttributeValue("id","productName");
    String str = elements.get(0).ownText();


        //elements.attr(ProjectConstants.H1, ProjectConstants.H1_ID_NAME);

//tag =h1
//key = id
//value = productName


    //Elements producDescriptionH3 = elements.attr(ProjectConstants.H3);
    return null;
  }
}
