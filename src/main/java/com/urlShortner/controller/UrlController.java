package com.urlShortner.controller;

import com.urlShortner.dto.ShortenUrlRequest;
import com.urlShortner.dto.ShortenUrlResponse;
import com.urlShortner.entity.Url;
import com.urlShortner.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
public class UrlController {

        private final UrlService urlService;
        public UrlController(UrlService urlService){
                this.urlService = urlService;
        }
        @PostMapping("/shorten")
        public ShortenUrlResponse shorten( @Valid @RequestBody ShortenUrlRequest request){
                return urlService.createShortUrl(request);
        }



}
