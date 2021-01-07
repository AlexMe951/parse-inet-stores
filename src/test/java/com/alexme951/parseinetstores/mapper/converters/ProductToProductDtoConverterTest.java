package com.alexme951.parseinetstores.mapper.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.alexme951.parseinetstores.repository.dto.parsing.ProductParsingDto;
import com.alexme951.parseinetstores.service.dto.Product;
import com.google.gson.GsonBuilder;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Tests for {@link ProductToProductDtoConverter}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductToProductDtoConverterTest {

  private final ProductToProductDtoConverter converter = new ProductToProductDtoConverter(
      new GsonBuilder().create());

  @Test
  public void convertTo() {
    // given
    String productName = "Product Name";
    String productDescription = "Product Description";
    String productCode = "Product Code";
    Product product = new Product(
        productName,
        productDescription,
        productCode,
        Map.of(
            "Attribute Key 1", "Attribute value 1",
            "Attribute Key 2", "Attribute value 2"
        )
    );
    String attributesJson = "{\"Attribute Key 2\":\"Attribute value 2\",\"Attribute Key 1\":\"Attribute value 1\"}";

    // when
    ProductParsingDto result = converter.convertTo(product, null, null);

    // then
    assertEquals(productName, result.getName());
    assertEquals(productDescription, result.getDescription());
    assertEquals(productCode, result.getCode());
    assertTrue(result.getAttributes().contains("\"Attribute Key 2\":\"Attribute value 2\""));
    assertTrue(result.getAttributes().contains("\"Attribute Key 1\":\"Attribute value 1\""));
  }

  @Test
  public void convertFrom() {
    // given
    String productName = "Product Name 1";
    String productDescription = "Product Description 1";
    String productCode = "Product Code 1";
    Product product = new Product(
        productName,
        productDescription,
        productCode,
        Map.of(
            "Attribute Key 3", "Attribute value 3",
            "Attribute Key 4", "Attribute value 4"
        )
    );
    String attributesJson = "{\"Attribute Key 3\":\"Attribute value 3\",\"Attribute Key 4\":\"Attribute value 4\"}";
    ProductParsingDto productParsingDto = new ProductParsingDto();
    productParsingDto.setName(productName);
    productParsingDto.setDescription(productDescription);
    productParsingDto.setCode(productCode);
    productParsingDto.setAttributes(attributesJson);

    // when
    Product result = converter.convertFrom(productParsingDto, null, null);

    // then
    assertEquals(product, result);
  }
}