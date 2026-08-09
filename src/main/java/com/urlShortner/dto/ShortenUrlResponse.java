package com.urlShortner.dto;

import org.springframework.stereotype.Service;


public class ShortenUrlResponse {


    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
