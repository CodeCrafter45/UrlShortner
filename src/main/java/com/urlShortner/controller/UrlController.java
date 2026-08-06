package com.urlShortner.controller;

import com.urlShortner.dto.ShortenUrlRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
public class UrlController {

        @PostMapping("/shorten")
        public void shorten(@RequestBody ShortenUrlRequest request){

        }



}
