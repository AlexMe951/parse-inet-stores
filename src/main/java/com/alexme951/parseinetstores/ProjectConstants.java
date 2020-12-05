package com.alexme951.parseinetstores;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectConstants {

  public static final int PARSING_TIMEOUT_MS = 10000;
  public static final String HREF_HTML_ATTR_KEY = "href";
  public static final String AUCHAN_WWW_LINK_PREFIX = "https://www.auchan.ru";
  public static final String AUCHAN_CATALOG_LINK_PREFIX = "https://www.auchan.ru/catalog/";
  public static final String AUCHAN_DEFAULT_CATEGORY_URL = "https://www.auchan.ru/catalog/ptica-myaso/";
  public static final String CATALOG_LINKS_SELECT_QUERY = "[href^=/catalog/]";
  public static final String PRODUCT_LINKS_SELECT_QUERY = "[href^=/product/]";
  public static final String CATALOG_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE = "[href^=/catalog/%s]";
  public static final String PRODUCT_CATEGORY_LINKS_SELECT_QUERY_TEMPLATE = "[href^=/product/%s]";

}
