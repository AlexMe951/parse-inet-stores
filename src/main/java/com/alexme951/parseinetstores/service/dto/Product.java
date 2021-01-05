package com.alexme951.parseinetstores.service.dto;

import java.util.Map;

public final record Product(
    String name,
    String description,
    String code,
    Map<String, String> attributes) {

}
