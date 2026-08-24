package com.urlShortner.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlStatsResponse {

    String shortCode;
    String originalUrl;
    Long clickCount;
}
