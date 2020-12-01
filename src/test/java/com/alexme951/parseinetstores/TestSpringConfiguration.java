package com.alexme951.parseinetstores;

import com.alexme951.parseinetstores.repository.CatalogLinkRepository;
import com.alexme951.parseinetstores.service.JsoupFacadeService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestSpringConfiguration {

  @Bean
  @Primary
  public CatalogLinkRepository getTestRepository() {
    return Mockito.mock(CatalogLinkRepository.class);
  }

  @Bean
  @Primary
  public JsoupFacadeService getJsoupFacadeService() {
    return Mockito.mock(JsoupFacadeService.class);
  }
}
