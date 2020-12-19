package com.alexme951.parseinetstores.bpp;

import java.lang.reflect.Field;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
@Slf4j
public class TestAnnotationBeanPostProcessor implements BeanPostProcessor {

  @SneakyThrows
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    TestAnnotation annotation = AnnotationUtils.findAnnotation(bean.getClass(), TestAnnotation.class);
    if (annotation != null){
      log.info("Bean with @TestAnnotation found: {}", beanName);
      log.info("Annotation value: {}", annotation.value());
    }
    return bean;
  }

  @SneakyThrows
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    TestAnnotation annotation = AnnotationUtils.findAnnotation(bean.getClass(), TestAnnotation.class);
    if (annotation != null){
      Field field = ReflectionUtils.findField(bean.getClass(), "value");
      if (field != null){
        log.info("field = {}", field);
        field.setAccessible(true);
        field.set(bean, annotation.value());
      }
    }
    return bean;
  }
}
