package com.alexme951.parseinetstores.service.parsing.impl;

import static com.alexme951.parseinetstores.ProjectConstants.PARSING_TIMEOUT_MS;
import static org.junit.Assert.assertEquals;

import com.alexme951.parseinetstores.service.dto.Product;
import com.alexme951.parseinetstores.service.dto.ProductLink;
import com.alexme951.parseinetstores.service.jsoup.JsoupFacadeService;
import java.net.URL;
import java.util.Map;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Tests for {@link ExtractProductInfoServiceImpl}.
 * <p>
 */

public class ExtractProductInfoServiceImplTest {

  private final JsoupFacadeService jsoupFacadeService = new JsoupFacadeService() {
    @Override
    public Elements parsePage(String url) {
      try {
        Document result = Jsoup.parse(productPageHtmlExample);
        return result.body().getAllElements();
      } catch (Exception ex) {
        return new Elements();
      }
    }
  };

  private final ExtractProductInfoServiceImpl extractProductInfoService = new ExtractProductInfoServiceImpl(
      jsoupFacadeService);

  @Test
  public void extractInfo() {
//    given
    ProductLink productLink = new ProductLink();
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

  private final static String productPageHtmlExample = """
      <!DOCTYPE html>
      <!-- saved from url=(0075)https://www.auchan.ru/product/myakot-bedra-govyazhego-miratorg-angus-800-g/ -->
      <html lang="ru" dir="ltr" class="js-f<div class=" css-f3hshq">
      <div id="main">
        <div class="alert"><img width="19"
                                src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/union.svg"><span>Режим работы в праздники. <a
            href="https://auchan.ru/rezhim-raboty-v-prednovogodnie-i-prazdnichnye-dni/" style="text-decoration: underline"
            dataga-autotrack="link">Подробнее</a></span>
          <button class="alertclose" dataga-autotrack="button"></button>
        </div>
        <script type="application/ld+json">{
          "@context": "https://schema.org",
          "@type": "Product",
          "name": "Мякоть бедра «Мираторг» говяжьего Ангус, 800 г",
          "brand": "Мираторг",
          "image": "/files/compressed/2475038/620/0/orig",
          "description": "Бедро идеально подойдет для запекания, но чтобы сделать его по-настоящему нежным, готовить мясо нужно медленно. Тогда вы получите удивительно вкусное блюдо с хрустящей корочкой и глубоким ароматом.",
          "offers": {
            "@type": "Offer",
            "price": "626.49",
            "availability": "InStock",
            "priceCurrency": "RUB"
          }
        }</script>
        <section class="css-w9ntn5-Section">
          <div class="css-1tbngli-Container">
            <nav aria-label="Вы находитесь здесь:">
              <ol itemscope="" itemtype="http://schema.org/BreadcrumbList" class="css-k008qs">
                <li itemprop="itemListElement" itemscope="" itemtype="http://schema.org/ListItem" class="css-1brot25">
                  <svg width="16" height="16" viewBox="0 0 16 16" class="css-ybmp0g">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                          d="M6.14645 4.64645C6.34171 4.45118 6.65829 4.45118 6.85355 4.64645L9.85355 7.64645C10.0488 7.84171 10.0488 8.15829 9.85355 8.35355L6.85355 11.3536C6.65829 11.5488 6.34171 11.5488 6.14645 11.3536C5.95118 11.1583 5.95118 10.8417 6.14645 10.6464L8.79289 8L6.14645 5.35355C5.95118 5.15829 5.95118 4.84171 6.14645 4.64645Z"></path>
                  </svg>
                  <a id="breadcrumbHomePage" itemprop="item" class="css-1oq3yy1" href="https://www.auchan.ru/"
                     dataga-autotrack="bredcrumbs"><span itemprop="name">Главная</span>
                    <meta itemprop="position" content="1">
                  </a></li>
                <li itemprop="itemListElement" itemscope="" itemtype="http://schema.org/ListItem" class="css-1brot25">
                  <svg width="16" height="16" viewBox="0 0 16 16" class="css-ybmp0g">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                          d="M6.14645 4.64645C6.34171 4.45118 6.65829 4.45118 6.85355 4.64645L9.85355 7.64645C10.0488 7.84171 10.0488 8.15829 9.85355 8.35355L6.85355 11.3536C6.65829 11.5488 6.34171 11.5488 6.14645 11.3536C5.95118 11.1583 5.95118 10.8417 6.14645 10.6464L8.79289 8L6.14645 5.35355C5.95118 5.15829 5.95118 4.84171 6.14645 4.64645Z"></path>
                  </svg>
                  <a itemprop="item" class="css-1oq3yy1" href="https://www.auchan.ru/catalog/ptica-myaso/"
                     dataga-autotrack="bredcrumbs"><span itemprop="name">Птица, мясо</span>
                    <meta itemprop="position" content="2">
                  </a></li>
                <li itemprop="itemListElement" itemscope="" itemtype="http://schema.org/ListItem" class="css-1brot25">
                  <svg width="16" height="16" viewBox="0 0 16 16" class="css-ybmp0g">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                          d="M6.14645 4.64645C6.34171 4.45118 6.65829 4.45118 6.85355 4.64645L9.85355 7.64645C10.0488 7.84171 10.0488 8.15829 9.85355 8.35355L6.85355 11.3536C6.65829 11.5488 6.34171 11.5488 6.14645 11.3536C5.95118 11.1583 5.95118 10.8417 6.14645 10.6464L8.79289 8L6.14645 5.35355C5.95118 5.15829 5.95118 4.84171 6.14645 4.64645Z"></path>
                  </svg>
                  <a itemprop="item" class="css-1oq3yy1"
                     href="https://www.auchan.ru/catalog/ptica-myaso/govyadina-i-telyatina-steyki/"
                     dataga-autotrack="bredcrumbs"><span itemprop="name">Говядина и телятина, стейки</span>
                    <meta itemprop="position" content="3">
                  </a></li>
                <li itemprop="itemListElement" itemscope="" itemtype="http://schema.org/ListItem" class="css-1brot25">
                  <svg width="16" height="16" viewBox="0 0 16 16" class="css-ybmp0g">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                          d="M6.14645 4.64645C6.34171 4.45118 6.65829 4.45118 6.85355 4.64645L9.85355 7.64645C10.0488 7.84171 10.0488 8.15829 9.85355 8.35355L6.85355 11.3536C6.65829 11.5488 6.34171 11.5488 6.14645 11.3536C5.95118 11.1583 5.95118 10.8417 6.14645 10.6464L8.79289 8L6.14645 5.35355C5.95118 5.15829 5.95118 4.84171 6.14645 4.64645Z"></path>
                  </svg>
                  <a itemprop="item" class="css-1oq3yy1"
                     href="https://www.auchan.ru/catalog/ptica-myaso/govyadina-i-telyatina-steyki/myakot/"
                     dataga-autotrack="bredcrumbs"><span itemprop="name">Мякоть</span>
                    <meta itemprop="position" content="4">
                  </a></li>
                <li itemprop="itemListElement" itemscope="" itemtype="http://schema.org/ListItem" class="css-1brot25">
                  <svg width="16" height="16" viewBox="0 0 16 16" class="css-ybmp0g">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                          d="M6.14645 4.64645C6.34171 4.45118 6.65829 4.45118 6.85355 4.64645L9.85355 7.64645C10.0488 7.84171 10.0488 8.15829 9.85355 8.35355L6.85355 11.3536C6.65829 11.5488 6.34171 11.5488 6.14645 11.3536C5.95118 11.1583 5.95118 10.8417 6.14645 10.6464L8.79289 8L6.14645 5.35355C5.95118 5.15829 5.95118 4.84171 6.14645 4.64645Z"></path>
                  </svg>
                  <span itemprop="name" class="css-xnoh5a">Мякоть бедра «Мираторг» говяжьего Ангус, 800 г</span>
                  <meta itemprop="position" content="5">
                </li>
              </ol>
            </nav>
          </div>
        </section>
        <main>
          <form action="https://www.auchan.ru/product/myakot-bedra-govyazhego-miratorg-angus-800-g/#" class="form"
                novalidate="">
            <div class="css-1hent27">
              <div class="css-1e4ocx0">
                <div class="css-8atqhb css-1tbngli-Container">
                  <div class="css-1phy807 css-9lnao0-Layout">
                    <div class="css-m1j0ms css-1jmuu35-Item">
                      <div class="product-carousel css-8atqhb css-11eyulh-Layout">
                        <div class="css-10klw3m css-tmu97a-Item">
                          <div class="css-1y7m5n0">
                            <div class="swiper-container swiper-container-initialized swiper-container-horizontal">
                              <div class="swiper-wrapper" style="transform: translate3d(0px, 0px, 0px);">
                                <div class="swiper-slide css-1hvjab5 swiper-slide-active" style="width: 330px;">
                                  <div
                                      style="cursor: crosshair; width: auto; height: auto; font-size: 0px; position: relative; user-select: none;">
                                    <img
                                        src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-2475038.orig"
                                        alt="Мякоть бедра «Мираторг» говяжьего Ангус, 800 г"
                                        style="max-height: 240px; width: 100%; height: auto; display: block; pointer-events: none; max-width: 197.347px;">
                                    <div>
                                      <div
                                          style="width: 100%; height: 0px; inset: 0px auto auto; display: block; background-color: rgba(0, 0, 0, 0.4); position: absolute; opacity: 0; transition: opacity 300ms ease-in 0s;"></div>
                                      <div
                                          style="width: 0px; height: 76px; inset: 0px auto auto 0px; display: block; background-color: rgba(0, 0, 0, 0.4); position: absolute; opacity: 0; transition: opacity 300ms ease-in 0s;"></div>
                                      <div
                                          style="width: 70px; height: 76px; inset: 0px 0px auto auto; display: block; background-color: rgba(0, 0, 0, 0.4); position: absolute; opacity: 0; transition: opacity 300ms ease-in 0s;"></div>
                                      <div
                                          style="width: 100%; height: 164px; inset: 76px auto auto; display: block; background-color: rgba(0, 0, 0, 0.4); position: absolute; opacity: 0; transition: opacity 300ms ease-in 0s;"></div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="null"></div>
                              <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="css-8g2cy1-Item">
                      <div id="zoom-image-portal" class="css-15zqrqa"></div>
                      <h1 id="productName" class="css-1xdlf13">Мякоть бедра «Мираторг» говяжьего Ангус, 800 г</h1></div>
                    <div class="css-1ip118y-Item">
                      <div class="addToCartBlock css-1psp37a">
                        <div class="fullPricePDP css-1129a1l">626.49 <span class="css-3tw024">C</span><span
                            class="css-1tkmjz0"> / шт</span></div>
                        <div class="css-1t4foex">
                          <svg width="16" height="16" viewBox="0 0 16 16" class="css-tbzb6z">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                  d="M8 16C12.4183 16 16 12.4183 16 8C16 3.58172 12.4183 0 8 0C3.58172 0 0 3.58172 0 8C0 12.4183 3.58172 16 8 16ZM13.5 5.67502C13.8728 5.26754 13.8447 4.635 13.4372 4.2622C13.0297 3.8894 12.3972 3.91751 12.0244 4.32499L7.30121 9.56202L5.41421 7.67502C5.02369 7.28449 4.39052 7.28449 4 7.67502C3.60948 8.06554 3.60948 8.69871 4 9.08923L6.62623 11.7155C6.81921 11.9084 7.08269 12.0142 7.35554 12.0081C7.62839 12.002 7.88691 11.8847 8.07114 11.6834L13.5 5.67502Z"></path>
                          </svg>
                          <span class="inStockData css-17mx28f">В наличии: 6 шт.</span></div>
                        <div class="css-zv4bwb">
                          <div class="css-79elbk">
                            <button type="submit" id="addToCartPDP" class="css-10bvuay css-1d47yl5">
                              <svg width="24" height="24" viewBox="0 0 24 24" class="css-5zprff">
                                <path fill-rule="evenodd" clip-rule="evenodd"
                                      d="M18.5 17.2575C18.7761 17.2575 19 17.0336 19 16.7575C19 16.4813 18.7761 16.2575 18.5 16.2575L8.37953 16.2575L7.70179 12.89H7.7204H7.75198H7.7836H7.81523H7.84689H7.87857H7.91028H7.94201H7.97376H8.00553H8.03733H8.06914H8.10098H8.13284H8.16472H8.19661H8.22853H8.26047H8.29242H8.32439H8.35639H8.38839H8.42042H8.45247H8.48453H8.5166H8.54869H8.5808H8.61293H8.64507H8.67722H8.70939H8.74157H8.77376H8.80597H8.83819H8.87043H8.90268H8.93493H8.9672H8.99949H9.03178H9.06408H9.09639H9.12872H9.16105H9.19339H9.22574H9.2581H9.29046H9.32284H9.35522H9.38761H9.42H9.45241H9.48481H9.51723H9.54965H9.58207H9.6145H9.64693H9.67937H9.71181H9.74425H9.7767H9.80915H9.8416H9.87405H9.90651H9.93897H9.97142H10.0039H10.0363H10.0688H10.1013H10.1337H10.1662H10.1986H10.2311H10.2635H10.296H10.3284H10.3608H10.3933H10.4257H10.4581H10.4906H10.523H10.5554H10.5878H10.6202H10.6526H10.685H10.7174H10.7497H10.7821H10.8145H10.8468H10.8792H10.9115H10.9438H10.9761H11.0084H11.0407H11.073H11.1053H11.1375H11.1698H11.202H11.2342H11.2664H11.2986H11.3308H11.363H11.3951H11.4273H11.4594H11.4915H11.5236H11.5557H11.5877H11.6198H11.6518H11.6838H11.7158H11.7478H11.7797H11.8117H11.8436H11.8755H11.9073H11.9392H11.971H12.0028H12.0346H12.0664H12.0982H12.1299H12.1616H12.1933H12.2249H12.2566H12.2882H12.3198H12.3513H12.3829H12.4144H12.4459H12.4773H12.5087H12.5402H12.5715H12.6029H12.6342H12.6655H12.6968H12.728H12.7592H12.7904H12.8215H12.8527H12.8838H12.9148H12.9458H12.9768H13.0078H13.0387H13.0696H13.1005H13.1313H13.1621H13.1929H13.2236H13.2543H13.285H13.3156H13.3462H13.3767H13.4072H13.4377H13.4682H13.4986H13.5289H13.5593H13.5896H13.6198H13.65H13.6802H13.7103H13.7404H13.7705H13.8005H13.8305H13.8604H13.8903H13.9201H13.9499H13.9797H14.0094H14.0391H14.0687H14.0983H14.1278H14.1573H14.1867H14.2161H14.2455H14.2748H14.3041H14.3333H14.3624H14.3916H14.4206H14.4497H14.4786H14.5076H14.5364H14.5653H14.594H14.6228H14.6514H14.6801H14.7086H14.7371H14.7656H14.794H14.8224H14.8507H14.8789H14.9071H14.9353H14.9634H14.9914H15.0194H15.0473H15.0752H15.103H15.1307H15.1584H15.1861H15.2137H15.2412H15.2687H15.2961H15.3234H15.3507H15.3779H15.4051H15.4322H15.4592H15.4862H15.5131H15.54H15.5668H15.5935H15.6202H15.6468H15.6734H15.6998H15.7263H15.7526H15.7789H15.8051H15.8313H15.8574H15.8834H15.9093H15.9352H15.9611H15.9868H16.0125H16.0381H16.0637H16.0891H16.1145H16.1399H16.1651H16.1903H16.2155H16.2405H16.2655H16.2904H16.3153H16.34H16.3647H16.3893H16.4139H16.4384H16.4628H16.4871H16.5113H16.5355H16.5596H16.5836H16.6076H16.6314H16.6552H16.6789H16.7026H16.7261H16.7496H16.773H16.7963H16.8196H16.8427H16.8658H16.8888H16.9117H16.9346H16.9573H16.98H17.0026H17.0251H17.0475H17.0698H17.0921H17.1143H17.1364H17.1584H17.1803H17.2021H17.2239H17.2455H17.2671H17.2886H17.31H17.3313H17.3525H17.3737H17.3947H17.4157H17.4366H17.4573H17.478H17.4986H17.5191H17.5396H17.5599H17.5801H17.6003H17.6203H17.6403H17.6601H17.6799H17.6996H17.7192H17.7387H17.758H17.7773H17.7966H17.8157H17.8347H17.8536H17.8724H17.8911H17.9097H17.9283H17.9467H17.965H17.9833H18.0014H18.0194H18.0374H18.0552H18.0729H18.0905H18.1081H18.1255H18.1428H18.16H18.1772H18.1942H18.2111H18.2279H18.2446H18.2612H18.2777H18.2941H18.3104H18.3265H18.3426H18.3586H18.3744H18.3902H18.4058H18.4214H18.4368H18.4521H18.4673H18.4824H18.4974H18.5123H18.5271H18.5417H18.5563H18.5707H18.585H18.5992H18.6133H18.6273H18.6412H18.655H18.6686H18.6822H18.6956H18.7089H18.7221H18.7351H18.7481H18.7609H18.7737H18.7863H18.7988H18.8111H18.8234H18.8355H18.8475H18.8594H18.8712H18.8829H18.8944H18.9058H18.9171H18.9283H18.9394H18.9503H18.9611H18.9718H18.9824H18.9928H19.0031H19.0133H19.0234H19.0334H19.0432H19.0529H19.0625H19.0719H19.0812H19.0904H19.0995H19.1084H19.1172H19.1259H19.1345H19.1429H19.1512H19.1594H19.1674H19.1753H19.1831H19.1907H19.1983H19.2056H19.2129H19.22H19.227H19.2339H19.2406H19.2472H19.2536H19.2599H19.2661H19.2722H19.2781H19.2839H19.2895H19.295H19.3004H19.3056H19.3107H19.3157H19.3205H19.3252H19.3298H19.3342H19.3384H19.3426H19.3465H19.3504H19.3541H19.3577H19.3611H19.3644H19.3675H19.3705H19.3734H19.3761H19.3786H19.3811H19.3833H19.3855H19.3875H19.3893H19.391H19.3926H19.394H19.3952H19.3963H19.3973H19.3981H19.3988H19.3993H19.3997H19.3999L19.4 12.39V12.89C19.6294 12.89 19.8293 12.734 19.885 12.5115L21.485 6.12146C21.5224 5.97207 21.4889 5.81378 21.3941 5.69238C21.2994 5.57098 21.154 5.50001 21 5.50001H6.21447L5.4902 1.90135C5.44322 1.66792 5.23814 1.5 5.00003 1.5L1.00003 1.5C0.723888 1.5 0.500031 1.72386 0.500031 2C0.50003 2.27614 0.723888 2.5 1.00003 2.5H4.59064L7.47997 16.8561C7.52695 17.0896 7.73202 17.2575 7.97014 17.2575L18.5 17.2575ZM6.41573 6.50001H20.3594L19.0097 11.89H19.0031H18.9928H18.9824H18.9718H18.9611H18.9503H18.9394H18.9283H18.9171H18.9058H18.8944H18.8829H18.8712H18.8594H18.8475H18.8355H18.8234H18.8111H18.7988H18.7863H18.7737H18.7609H18.7481H18.7351H18.7221H18.7089H18.6956H18.6822H18.6686H18.655H18.6412H18.6273H18.6133H18.5992H18.585H18.5707H18.5563H18.5417H18.5271H18.5123H18.4974H18.4824H18.4673H18.4521H18.4368H18.4214H18.4058H18.3902H18.3744H18.3586H18.3426H18.3265H18.3104H18.2941H18.2777H18.2612H18.2446H18.2279H18.2111H18.1942H18.1772H18.16H18.1428H18.1255H18.1081H18.0905H18.0729H18.0552H18.0374H18.0194H18.0014H17.9833H17.965H17.9467H17.9283H17.9097H17.8911H17.8724H17.8536H17.8347H17.8157H17.7966H17.7773H17.758H17.7387H17.7192H17.6996H17.6799H17.6601H17.6403H17.6203H17.6003H17.5801H17.5599H17.5396H17.5191H17.4986H17.478H17.4573H17.4366H17.4157H17.3947H17.3737H17.3525H17.3313H17.31H17.2886H17.2671H17.2455H17.2239H17.2021H17.1803H17.1584H17.1364H17.1143H17.0921H17.0698H17.0475H17.0251H17.0026H16.98H16.9573H16.9346H16.9117H16.8888H16.8658H16.8427H16.8196H16.7963H16.773H16.7496H16.7261H16.7026H16.6789H16.6552H16.6314H16.6076H16.5836H16.5596H16.5355H16.5113H16.4871H16.4628H16.4384H16.4139H16.3893H16.3647H16.34H16.3153H16.2904H16.2655H16.2405H16.2155H16.1903H16.1651H16.1399H16.1145H16.0891H16.0637H16.0381H16.0125H15.9868H15.9611H15.9352H15.9093H15.8834H15.8574H15.8313H15.8051H15.7789H15.7526H15.7263H15.6998H15.6734H15.6468H15.6202H15.5935H15.5668H15.54H15.5131H15.4862H15.4592H15.4322H15.4051H15.3779H15.3507H15.3234H15.2961H15.2687H15.2412H15.2137H15.1861H15.1584H15.1307H15.103H15.0752H15.0473H15.0194H14.9914H14.9634H14.9353H14.9071H14.8789H14.8507H14.8224H14.794H14.7656H14.7371H14.7086H14.6801H14.6514H14.6228H14.594H14.5653H14.5364H14.5076H14.4786H14.4497H14.4206H14.3916H14.3624H14.3333H14.3041H14.2748H14.2455H14.2161H14.1867H14.1573H14.1278H14.0983H14.0687H14.0391H14.0094H13.9797H13.9499H13.9201H13.8903H13.8604H13.8305H13.8005H13.7705H13.7404H13.7103H13.6802H13.65H13.6198H13.5896H13.5593H13.5289H13.4986H13.4682H13.4377H13.4072H13.3767H13.3462H13.3156H13.285H13.2543H13.2236H13.1929H13.1621H13.1313H13.1005H13.0696H13.0387H13.0078H12.9768H12.9458H12.9148H12.8838H12.8527H12.8215H12.7904H12.7592H12.728H12.6968H12.6655H12.6342H12.6029H12.5715H12.5402H12.5087H12.4773H12.4459H12.4144H12.3829H12.3513H12.3198H12.2882H12.2566H12.2249H12.1933H12.1616H12.1299H12.0982H12.0664H12.0346H12.0028H11.971H11.9392H11.9073H11.8755H11.8436H11.8117H11.7797H11.7478H11.7158H11.6838H11.6518H11.6198H11.5877H11.5557H11.5236H11.4915H11.4594H11.4273H11.3951H11.363H11.3308H11.2986H11.2664H11.2342H11.202H11.1698H11.1375H11.1053H11.073H11.0407H11.0084H10.9761H10.9438H10.9115H10.8792H10.8468H10.8145H10.7821H10.7497H10.7174H10.685H10.6526H10.6202H10.5878H10.5554H10.523H10.4906H10.4581H10.4257H10.3933H10.3608H10.3284H10.296H10.2635H10.2311H10.1986H10.1662H10.1337H10.1013H10.0688H10.0363H10.0039H9.97142H9.93897H9.90651H9.87405H9.8416H9.80915H9.7767H9.74425H9.71181H9.67937H9.64693H9.6145H9.58207H9.54965H9.51723H9.48481H9.45241H9.42H9.38761H9.35522H9.32284H9.29046H9.2581H9.22574H9.19339H9.16105H9.12872H9.09639H9.06408H9.03178H8.99949H8.9672H8.93493H8.90268H8.87043H8.83819H8.80597H8.77376H8.74157H8.70939H8.67722H8.64507H8.61293H8.5808H8.54869H8.5166H8.48453H8.45247H8.42042H8.38839H8.35639H8.32439H8.29242H8.26047H8.22853H8.19661H8.16472H8.13284H8.10098H8.06914H8.03733H8.00553H7.97376H7.94201H7.91028H7.87857H7.84689H7.81523H7.7836H7.75198H7.7204H7.68883H7.65729H7.62578H7.59429H7.56283H7.5314H7.50053L6.41573 6.50001ZM11 21.5C11 22.3284 10.3284 23 9.49999 23C8.67157 23 7.99999 22.3284 7.99999 21.5C7.99999 20.6716 8.67157 20 9.49999 20C10.3284 20 11 20.6716 11 21.5ZM18 21.5C18 22.3284 17.3284 23 16.5 23C15.6716 23 15 22.3284 15 21.5C15 20.6716 15.6716 20 16.5 20C17.3284 20 18 20.6716 18 21.5Z"></path>
                              </svg>
                              В корзину
                            </button>
                          </div>
                        </div>
                        <div class="css-1e2955z"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="css-1t4ksn1">
              <section class="css-w9ntn5-Section">
                <div class="css-1tbngli-Container">
                  <div class="css-1miid4q"><h2 class="css-3a7r9n">О товаре</h2>
                    <h3 class="css-1cpr8qh">Описание</h3>
                    <div class="css-ivaahx">Бедро идеально подойдет для запекания, но чтобы сделать его по-настоящему нежным,
                      готовить мясо нужно медленно. Тогда вы получите удивительно вкусное блюдо с хрустящей корочкой и
                      глубоким ароматом.
                    </div>
                    <h3 class="css-1cpr8qh">Характеристики</h3>
                    <table class="css-p83b4h">
                      <tbody>
                      <tr>
                        <th class="css-12mfum8">Артикул товара</th>
                        <td class="css-c17atn">159729</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Бренд</th>
                        <td class="css-c17atn">Мираторг</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Калорийность</th>
                        <td class="css-c17atn">230</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Белки на 100 г, г</th>
                        <td class="css-c17atn">16</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Жиры на 100 г, г</th>
                        <td class="css-c17atn">18</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Масса нетто, кг</th>
                        <td class="css-c17atn">0.8</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Масса брутто, кг</th>
                        <td class="css-c17atn">0.8</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Ингредиенты</th>
                        <td class="css-c17atn">говядина</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">ДxШxВ, мм</th>
                        <td class="css-c17atn">180x150x60</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Производитель</th>
                        <td class="css-c17atn">ООО «Брянская мясная компания»</td>
                      </tr>
                      <tr>
                        <th class="css-12mfum8">Страна производства</th>
                        <td class="css-c17atn">Россия</td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                  <div data-retailrocket-markup-block="5ede0b4597a52825cc893f9f" data-stock-id="734" data-product-id="159729"
                       initialized="true"></div>
                </div>
              </section>
              <div data-retailrocket-markup-block="5ede0b6097a52530444d9c53" data-stock-id="734" data-product-id="159729"
                   initialized="true"></div>
              <div data-retailrocket-markup-block="5ede0b6b97a52530444d9c54" data-stock-id="734" data-product-id="159729"
                   initialized="true"></div>
            </div>
          </form>
        </main>
        <div class="css-h49jl0">
          <section class="css-12zxd6z css-w9ntn5-Section">
            <div class="css-1tbngli-Container"><h2 id="YouMayNeedTitle" class="css-1cecrtc">Смотрите также</h2></div>
            <div id="YouMayNeedContainer" class="css-1gb3ku6 css-1tbngli-Container">
              <div class="css-r9q9wz css-fwwyn1-Layout"><a class="youMayNeedCategoryItem css-191a632"
                                                           href="https://www.auchan.ru/catalog/ptica-myaso/"
                                                           dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065854.orig"
                      alt="Птица, мясо" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Птица, мясо</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                   href="https://www.auchan.ru/catalog/hlebnaya-vypechka/"
                                                                   dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065682.orig"
                      alt="Хлебная выпечка" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Хлебная выпечка</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                       href="https://www.auchan.ru/catalog/moloko-syr-yayca/"
                                                                       dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065313.orig"
                      alt="Молоко, сыр, яйца" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Молоко, сыр, яйца</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                         href="https://www.auchan.ru/catalog/zamorozhennye-produkty/"
                                                                         dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064791.orig"
                      alt="Замороженные продукты" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Замороженные продукты</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                             href="https://www.auchan.ru/catalog/kolbasnye-izdeliya/"
                                                                             dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065014.orig"
                      alt="Колбасные изделия" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Колбасные изделия</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                         href="https://www.auchan.ru/catalog/ryba-ikra-moreprodukty/"
                                                                         dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065570.orig"
                      alt="Рыба, икра, морепродукты" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Рыба, икра, морепродукты</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                                href="https://www.auchan.ru/catalog/zdorovyy-vybor/"
                                                                                dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064835.orig"
                      alt="Здоровый выбор" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Здоровый выбор</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                      href="https://www.auchan.ru/catalog/ovoschi-frukty-zelen-griby-yagody/"
                                                                      dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065322.orig"
                      alt="Овощи, фрукты, зелень, грибы, ягоды" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Овощи, фрукты, зелень, грибы, ягоды</span></a><a
                  class="youMayNeedCategoryItem css-191a632" href="https://www.auchan.ru/catalog/alkogol/"
                  dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-5730745.orig"
                      alt="Алкоголь" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Алкоголь</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                href="https://www.auchan.ru/catalog/kulinariya/"
                                                                dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065092.orig"
                      alt="Кулинария" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Кулинария</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                 href="https://www.auchan.ru/catalog/bakaleya/"
                                                                 dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064364.orig"
                      alt="Бакалея" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Бакалея</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                               href="https://www.auchan.ru/catalog/orehi-suhofrukty-sneki/"
                                                               dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065361.orig"
                      alt="Орехи, сухофрукты, снеки" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Орехи, сухофрукты, снеки</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                                href="https://www.auchan.ru/catalog/chay-kofe-sladosti/"
                                                                                dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065714.orig"
                      alt="Чай, кофе, сладости" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Чай, кофе, сладости</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                           href="https://www.auchan.ru/catalog/voda-soki-napitki/"
                                                                           dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064411.orig"
                      alt="Вода, соки, напитки" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Вода, соки, напитки</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                           href="https://www.auchan.ru/catalog/zootovary/"
                                                                           dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064868.orig"
                      alt="Зоотовары" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Зоотовары</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                 href="https://www.auchan.ru/catalog/uborka-i-bytovaya-himiya/"
                                                                 dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065663.orig"
                      alt="Уборка и бытовая химия" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Уборка и бытовая химия</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                              href="https://www.auchan.ru/catalog/krasota-i-gigiena/"
                                                                              dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065030.orig"
                      alt="Красота и гигиена" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Красота и гигиена</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                         href="https://www.auchan.ru/catalog/vse-dlya-detey/"
                                                                         dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064434.orig"
                      alt="Все для детей" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Все для детей</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                     href="https://www.auchan.ru/catalog/tovary-dlya-dachi/"
                                                                     dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065630.orig"
                      alt="Товары для дачи" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Товары для дачи</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                       href="https://www.auchan.ru/catalog/kanctovary/"
                                                                       dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064945.orig"
                      alt="Канцтовары" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Канцтовары</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                  href="https://www.auchan.ru/catalog/elektronika/"
                                                                  dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065723.orig"
                      alt="Электроника" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Электроника</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                   href="https://www.auchan.ru/catalog/bytovaya-tehnika/"
                                                                   dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064393.orig"
                      alt="Бытовая техника" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Бытовая техника</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                       href="https://www.auchan.ru/catalog/tovary-dlya-doma/"
                                                                       dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065645.orig"
                      alt="Товары для дома" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Товары для дома</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                       href="https://www.auchan.ru/catalog/mebel/"
                                                                       dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065291.orig"
                      alt="Мебель" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Мебель</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                              href="https://www.auchan.ru/catalog/stroitelstvo-i-remont/"
                                                              dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065619.orig"
                      alt="Строительство и ремонт" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Строительство и ремонт</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                              href="https://www.auchan.ru/catalog/avtotovary/"
                                                                              dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4062415.orig"
                      alt="Автотовары" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Автотовары</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                  href="https://www.auchan.ru/catalog/sport-i-otdyh/"
                                                                  dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065589.orig"
                      alt="Спорт и отдых" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Спорт и отдых</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                     href="https://www.auchan.ru/catalog/odezhda-obuv-i-aksessuary/"
                                                                     dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4065341.orig"
                      alt="Одежда, обувь и аксессуары" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Одежда, обувь и аксессуары</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                                  href="https://www.auchan.ru/catalog/igrushki/"
                                                                                  dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064919.orig"
                      alt="Игрушки" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Игрушки</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                               href="https://www.auchan.ru/catalog/knigi-zhurnaly/"
                                                               dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-2670165.orig"
                      alt="Книги, журналы" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Книги, журналы</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                                      href="https://www.auchan.ru/catalog/bagazh/"
                                                                      dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-2670122.orig"
                      alt="Багаж" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Багаж</span></a><a class="youMayNeedCategoryItem css-191a632"
                                                             href="https://www.auchan.ru/catalog/dlya-prazdnika/"
                                                             dataga-autotrack="link">
                <div class="css-rsybel">
                  <picture class="picture css-3y8cxo"><img
                      src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/img-4064783.orig"
                      alt="Для праздника" draggable="false" class="picture__img"></picture>
                </div>
                <span class="css-12hfhk4">Для праздника</span></a></div>
            </div>
          </section>
        </div>
      </div>
      <div class="seoText css-16j0oak">
        <div class="css-1tbngli-Container">
          <div class="css-tzaoy2"><p>Предлагаем вашему вниманию возможность купить Мякоть бедра «Мираторг» говяжьего Ангус,
            800 г по цене 626.49 руб. Заказать можно на сайте Ашан или с помощью мобильного приложения в удобное для вас время
            суток.</p>
            <p><u><a href="https://www.auchan.ru/" dataga-autotrack="link">Доставка продуктов и товаров</a></u> производится
              по Москве, Московской области, Санкт-Петербургу, Ростову, Самаре, Нижнему Новгороду, Новосибирску, Краснодару,
              Татарстану, Саратову, Ярославлю, а также в другие регионы России.</p></div>
        </div>
      </div>
      <footer class="footer css-qbxonb">
        <div class="css-1tbngli-Container">
          <div class="css-y96ogu-Layout">
            <div class="css-glk6uj-Item"><a title="Перейти на главную страницу" id="linkToHomePageFromFooter"
                                            class="css-1yjvs5a" href="https://www.auchan.ru/" dataga-autotrack="link"
                                            dataga-autotracktext="Главная" data-gtm-vis-first-on-screen-1585327_1377="54"
                                            data-gtm-vis-total-visible-time-1585327_1377="1500"
                                            data-gtm-vis-has-fired-1585327_1377="1">
              <svg width="130" height="40" viewBox="0 0 130 40" fill="none">
                <path fill-rule="evenodd" clip-rule="evenodd"
                      d="M130 39.3643H122.276V30.3581H114.99V39.3643H107.231V15.4489H114.99V24.219H122.276V15.4489H130V39.3643ZM30.6617 10.4229C29.987 11.0976 29.987 12.2107 30.6617 12.8853C31.3363 13.5599 32.4494 13.5599 33.124 12.8853C33.7986 12.2107 33.7986 11.0976 33.124 10.4229C32.4494 9.74831 31.3363 9.74831 30.6617 10.4229ZM36.9019 11.8396C36.9019 11.8396 35.89 19.3954 32.9216 24.1853C32.8132 24.35 32.6874 24.5143 32.5454 24.6759C29.5456 28.9863 24.4023 29.5485 22.1951 29.5485L25.7369 39.398H23.5444L19.699 28.6715C19.6653 28.5029 19.699 28.4017 19.8002 28.368C21.3091 27.7775 21.6697 27.5698 22.7121 26.9694L22.8023 26.9175H22.8207C25.9917 24.8562 27.6972 21.4439 28.4017 19.7665C28.4354 19.6653 28.4354 19.5641 28.3342 19.4966C28.3289 19.4949 28.3237 19.4932 28.3185 19.4916C28.1213 19.8531 27.9233 20.2146 27.6933 20.576C24.9273 24.7587 20.7109 27.7271 13.8635 27.7271H4.38504C4.14893 27.7271 4.14893 27.4909 4.25012 27.3897L6.74623 24.8599C3.27192 24.8599 1.24804 22.8023 0.505958 20.5086C0.438495 20.3737 0.371033 20.1038 0.640883 20.1038H11.2662C12.3793 20.1038 13.1552 19.7665 13.796 19.1931L22.2626 12.1433C19.0918 14.8755 14.7743 19.0919 10.8614 24.4889C10.8315 24.5288 10.8222 24.5746 10.8275 24.6193C10.8311 24.5879 10.8418 24.5552 10.8614 24.5226C14.7743 19.1256 19.0919 14.9092 22.2626 12.177C25.0285 9.78204 26.9175 8.50026 26.9175 8.50026C28.233 7.65698 29.6834 7.21847 31.3363 7.21847C34.1178 7.21847 36.0003 8.88319 36.5539 9.93024L36.5646 9.91699H40.0389C40.275 9.91699 40.3425 10.2206 40.1401 10.3218L36.9019 11.8059L35.7551 11.3C35.659 11.276 35.6142 11.1836 35.6451 11.0837C35.5838 11.1958 35.6483 11.307 35.7551 11.3337L36.9019 11.8396ZM35.3166 23.1733C33.6975 26.5464 32.1458 28.233 28.5703 29.9533L31.9097 39.3643H41.287C39.3643 33.5963 37.273 28.1655 35.3166 23.1733ZM9.47846 39.3643H0C1.15237 35.7097 2.30473 32.5049 3.36299 29.5619L3.44058 29.3461H12.6829L9.47846 39.3643ZM11.0301 18.4172C11.7047 18.4172 12.3119 18.316 12.8179 17.8775L17.6077 13.931C18.5859 11.1988 19.6653 8.39907 20.7447 6.07161C21.3181 7.31967 21.8241 8.56772 22.3638 9.91697C24.9948 7.75817 26.6139 6.47639 28.7727 5.86923C27.4235 2.93461 26.479 0.910742 26.0405 0H15.4152C14.5044 1.88895 11.1313 8.90503 7.18474 18.4172H11.0301ZM71.9149 39.3643L71.4089 36.7333C69.7224 38.521 67.631 39.8703 63.8531 39.8365C60.6487 39.8365 58.4562 38.7571 57.2756 36.5983C55.5215 38.4536 53.329 39.8365 49.5511 39.8365C44.3228 39.8365 41.8267 37.0031 41.8267 31.2351V15.4489H49.5511V30.2231C49.5511 32.4494 50.6642 33.6975 52.6544 33.6975C53.9362 33.6975 55.4878 32.8542 56.1287 32.0446V15.4489H63.8531V30.2231C63.8531 32.4494 65.0337 33.6975 67.1925 33.6975C68.3731 33.6975 69.7898 32.8542 70.4307 32.0446V15.4489H78.1889V39.3643H71.9149ZM92.2211 33.6975C94.11 33.6975 95.2569 32.8542 95.9652 32.0446V21.4868C94.9196 21.2507 94.1775 21.1495 92.727 21.1495C90.2309 21.1495 88.9829 23.4432 88.9829 28.0306C88.9829 31.741 90.1297 33.6975 92.2211 33.6975ZM89.8599 39.8703H89.6238C83.8895 39.8365 80.9211 36.3285 80.9211 28.3342C80.9211 19.699 85.1375 14.9766 92.862 14.9766C98.2927 14.9766 101.194 15.6513 103.723 16.4608V39.3643H97.4157L97.0109 36.7333C95.6954 38.6559 93.3005 39.8365 89.8599 39.8703ZM26.7826 30.5942C26.1754 30.7629 25.467 30.8978 24.725 30.999L27.727 39.3643H29.9196L26.7826 30.5942ZM31.9772 10.423C31.6736 10.7266 31.6736 11.2663 31.9772 11.5699C32.2808 11.8734 32.8205 11.8734 33.1241 11.5699C33.4276 11.2663 33.4276 10.7266 33.1241 10.423C32.8205 10.1194 32.2808 10.1194 31.9772 10.423Z"
                      fill="white"></path>
              </svg>
            </a>
              <p class="css-nqj95z">©&nbsp;2001–2021&nbsp;ООО «АШАН»</p>
              <p class="css-1wd0f2h"><span class="css-hm1sg4">Все права защищены.</span> «Auchan» является зарегистрированным
                товарным знаком Auchan Holding&nbsp;SA. <a id="linkToPrivacyPolicy" class="css-awe853"
                                                           href="https://www.auchan.ru/privacy-policy/"
                                                           dataga-autotrack="link">Политика Конфиденциальности</a></p></div>
            <div class="css-eq2wz8-Item">
              <nav class="css-8atqhb">
                <div class="css-1ms4352">
                  <div class="css-1eu5exj"><a href="https://www.auchan.ru/" class="footerContentMenuCatName css-42vmhp"
                                              dataga-autotrack="link">Интернет-магазин</a></div>
                  <ol class="css-164r41r">
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/service/zakaz/" dataga-autotrack="link">Как
                      сделать заказ</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/service/dostavka/" dataga-autotrack="link">Условия
                      доставки</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/service/sposoby-oplaty/"
                                               dataga-autotrack="link">Оплата</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/service/vozvrat/" dataga-autotrack="link">Обмен
                      и возврат заказа</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/service/opt/" dataga-autotrack="link">Оптовым
                      покупателям</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/service/help/" dataga-autotrack="link">Вопросы
                      и ответы</a></li>
                  </ol>
                </div>
                <div class="css-9pdf6w">
                  <div class="css-1eu5exj"><a class="footerContentMenuCatName css-42vmhp"
                                              href="https://www.auchan.ru/help/company/" dataga-autotrack="link">О
                    компании</a></div>
                  <ol class="css-164r41r">
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/company/jobs/" dataga-autotrack="link">Работа
                      в АШАН</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/company/suppliers/" dataga-autotrack="link">Партнерам</a>
                    </li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/company/discount/" dataga-autotrack="link">Социальная
                      скидка</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/company/credit-card/" dataga-autotrack="link">Кредитная
                      карта</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/company/charitable-foundation/"
                                               dataga-autotrack="link">Благотворительный фонд</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/garantiya-svezhesti/" dataga-autotrack="link">Гарантия
                      свежести</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/company/legal-information/"
                                               dataga-autotrack="link">Правовая информация</a></li>
                    <li class="css-1s5s913"><a class="footerContentMenuLink css-42vmhp"
                                               href="https://www.auchan.ru/help/company/contacts/" dataga-autotrack="link">Контакты</a>
                    </li>
                  </ol>
                </div>
              </nav>
            </div>
            <div class="css-3m4rec-Item"><a id="phoneLinkFooter" href="tel:88007005800"
                                            aria-label="Позвонить по номеру 8 800 700-58-00" class="css-b7glu0"
                                            dataga-autotrack="link">8 800 700-58-00
              <svg width="24" height="24" viewBox="0 0 24 24" class="css-15ol4ma">
                <path
                    d="M10.771 13.229C12.354 14.811 14.188 16.325 14.913 15.6C15.95 14.563 16.59 13.659 18.878 15.498C21.165 17.336 19.408 18.562 18.403 19.566C17.243 20.726 12.919 19.628 8.645 15.355C4.372 11.081 3.277 6.757 4.438 5.597C5.443 4.591 6.663 2.835 8.501 5.122C10.34 7.409 9.437 8.049 8.398 9.087C7.676 9.812 9.189 11.646 10.771 13.229Z"></path>
              </svg>
            </a>
              <p class="css-1wd0f2h">Круглосуточная поддержка клиентов</p></div>
          </div>
        </div>
      </footer></div></div>
      <script id="__LOADABLE_REQUIRED_CHUNKS__" type="application/json">[
        13,
        21,
        77,
        11,
        72,
        70,
        74,
        6,
        12,
        14,
        27
      ]</script>
      <script async="" data-chunk="main"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/runtime.c6e5.js.download"></script>
      <script async="" data-chunk="main"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/vendor_main.chunk.d492.js.download"></script>
      <script async="" data-chunk="main"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/main.chunk.e57e.js.download"></script>
      <script async="" data-chunk="components-DeliveryPopup"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/vendor_ShopDetail_Shops_components-DeliveryPopup.chunk.f654.js.download"></script>
      <script async="" data-chunk="components-DeliveryPopup"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/vendor_Checkout_components-DeliveryPopup.chunk.f12f.js.download"></script>
      <script async="" data-chunk="components-DeliveryPopup"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/vendor_components-DeliveryPopup.chunk.5bd9.js.download"></script>
      <script async="" data-chunk="components-DeliveryPopup"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/commons_Cart_Home_ShopDetail_components-DeliveryPopup.chunk.5111.js.download"></script>
      <script async="" data-chunk="components-DeliveryPopup"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/components-DeliveryPopup.chunk.2fc6.js.download"></script>
      <script async="" data-chunk="components-AdultCheckPopup"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/components-AdultCheckPopup.chunk.4632.js.download"></script>
      <script async="" data-chunk="components-ShopSelectPopup"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/components-ShopSelectPopup.chunk.1c9d.js.download"></script>
      <script async="" data-chunk="Cart"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/vendor_Cart_CatalogCategory_CatalogDiscountCategory_CatalogSubCategory_Ca.download"></script>
      <script async="" data-chunk="Cart"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/commons_Cart_Order_ThankYou.chunk.02f1.js.download"></script>
      <script async="" data-chunk="Cart"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/commons_Cart_NotFound.chunk.ebf7.js.download"></script>
      <script async="" data-chunk="Cart"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/Cart.chunk.77c3.js.download"></script>
      <div class="popup"></div>
      <div class="popup"></div>
      <div class="popup"></div>
      <div class="popup popup--fullscreen-mobile"></div>
      <div class="popup"></div>
      <div class="popup"></div>
      <div class="popup"></div>
      <script type="text/javascript" id=""
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/jquery-3.5.1.min.js.download"></script>
      <iframe height="0" width="0" style="display: none; visibility: hidden;"
              src="./Купить Мякоть бедра «Мираторг» говяжьего Ангус, 800 г по цене 626.49 руб в интернет-магазине Ашан в Москве и России_files/activityi.html"></iframe>
      </body></html>
      """;


}