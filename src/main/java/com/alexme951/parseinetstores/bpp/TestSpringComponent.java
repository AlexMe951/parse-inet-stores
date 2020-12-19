package com.alexme951.parseinetstores.bpp;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@TestAnnotation("New field value")
@Slf4j
public class TestSpringComponent {

  @Value("${alexme951.property}")
  private String value = "default value";

  @PostConstruct
  public void doSomething(){
    log.info("Field value = {}", value);
  }
}

