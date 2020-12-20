package com.alexme951.parseinetstores.service.dto;

import java.time.OffsetDateTime;

public final record CategoryLink(String linkUrl, OffsetDateTime parsingTime,
                                 String parentCategoryLinkUrl) {

}
