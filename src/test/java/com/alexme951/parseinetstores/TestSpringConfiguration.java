package com.alexme951.parseinetstores;

import com.alexme951.parseinetstores.repository.parsing.CategoryLinkRepository;
import com.alexme951.parseinetstores.service.jsoup.impl.JsoupFacadeServiceImpl;
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
  public CategoryLinkRepository getTestRepository() {
    return Mockito.mock(CategoryLinkRepository.class);
  }

  @Bean
  @Primary
  public JsoupFacadeServiceImpl getJsoupFacadeService() {
    return Mockito.mock(JsoupFacadeServiceImpl.class);
  }
}
