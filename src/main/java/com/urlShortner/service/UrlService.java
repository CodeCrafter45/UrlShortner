package com.urlShortner.service;

import com.urlShortner.dto.ShortenUrlRequest;
import com.urlShortner.dto.ShortenUrlResponse;
import com.urlShortner.dto.UrlStatsResponse;
import com.urlShortner.entity.Url;
import com.urlShortner.exception.ShortUrlNotFoundException;
import com.urlShortner.repository.UrlRepository;
import com.urlShortner.util.ShortCodeGenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Value("${app.base-url}")
    private String baseUrl;

    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlService(UrlRepository urlRepository, ShortCodeGenerator shortCodeGenerator,
                      @Value("${app.base-url}") String baseUrl){
        this.urlRepository = urlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
        this.baseUrl = baseUrl;
    }
    public ShortenUrlResponse createShortUrl(ShortenUrlRequest request) {
        Url url = new Url();
        String original = request.getUrl();
        if (!original.startsWith("http://") && !original.startsWith("https://")) {
            original = "https://" + original;
        }
        url.setOriginalUrl(original);

        String code =shortCodeGenerator.generateShortCode();

        while (urlRepository.existsByShortCode(code)) {
            code = shortCodeGenerator.generateShortCode();
        }
            url.setShortCode(code);
        Url savedUrl = urlRepository.save(url);

        ShortenUrlResponse response = new ShortenUrlResponse();



         String shortUrl = baseUrl + "/r/" + savedUrl.getShortCode();
        response.setShortUrl(shortUrl);

        return response;

    }

    public String getOriginalUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortUrlNotFoundException("Short URL not found"));

        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url.getOriginalUrl();
    }

    public List<UrlStatsResponse> getAllStats(){
        List<Url> urls = urlRepository.findAll();
        List<UrlStatsResponse> responses = new ArrayList<>();

        for (Url url : urls) {
            UrlStatsResponse dto = new UrlStatsResponse();

            dto.setShortCode(url.getShortCode());
            dto.setOriginalUrl(url.getOriginalUrl());
            dto.setClickCount(url.getClickCount());

            responses.add(dto);
        }

        return responses;

    }



}
