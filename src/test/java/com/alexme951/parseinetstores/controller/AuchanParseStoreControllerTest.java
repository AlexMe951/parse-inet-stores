package com.alexme951.parseinetstores.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.alexme951.parseinetstores.repository.dto.CatalogLink;
import com.alexme951.parseinetstores.service.AuchanParseStoreCategoryService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class AuchanParseStoreControllerTest {

  private AuchanParseStoreController controller;

  @Mock
  private AuchanParseStoreCategoryService service;

  @Before
  public void setUp() {
    controller = new AuchanParseStoreController(service);
  }

  @Test
  public void getAllCatalogLinks() {
    // given
    when(service.parseAllCatalogLinks()).thenReturn(ImmutableList.of("testUrl"));
    // when
    ResponseEntity<List<String>> result = controller.getAllCatalogLinks();
    // then
    verify(service).parseAllCatalogLinks();
    verifyNoMoreInteractions(service);
    assertNotNull(result.getBody());
    assertEquals(1, result.getBody().size());
    assertEquals("testUrl", result.getBody().get(0));
  }

  @Test
  public void getAllSavedCatalogLinks() {
    // given
    LocalDateTime parsingTime = LocalDateTime.of(2000,1,1,1,1,1);
    CatalogLink catalogLink = new CatalogLink(1L, parsingTime, "testSavedUrl");
    when(service.getAllSavedCatalogLinks()).thenReturn(ImmutableList.of(catalogLink));
    // when
    ResponseEntity<Iterable<CatalogLink>> result = controller.getAllSavedCatalogLinks();
    // then
    verify(service).getAllSavedCatalogLinks();
    verifyNoMoreInteractions(service);
    assertNotNull(result.getBody());
    assertEquals(1, Lists.newArrayList(result.getBody()).size());
    assertEquals(catalogLink, Lists.newArrayList(result.getBody()).get(0));
  }
}