package com.urlShortner.service;

import com.urlShortner.dto.ShortenUrlRequest;
import com.urlShortner.dto.ShortenUrlResponse;
import com.urlShortner.entity.Url;
import com.urlShortner.exception.ShortUrlNotFoundException;
import com.urlShortner.repository.UrlRepository;
import com.urlShortner.util.ShortCodeGenerator;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlService(UrlRepository urlRepository, ShortCodeGenerator shortCodeGenerator){
        this.urlRepository = urlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }
    public ShortenUrlResponse createShortUrl(ShortenUrlRequest request) {
        Url url = new Url();
        url.setOriginalUrl(request.getUrl());

        String code =shortCodeGenerator.generateShortCode();

        while (urlRepository.existsByShortCode(code)) {
            code = shortCodeGenerator.generateShortCode();
        }
            url.setShortCode(code);
        Url savedUrl = urlRepository.save(url);

        ShortenUrlResponse response = new ShortenUrlResponse();



        String shortUrl = "http://localhost:8080/" + savedUrl.getShortCode();
        response.setShortUrl(shortUrl);

        return response;

    }

    public String getOriginalUrl(String shortCode) {

        Optional<Url> result =
                urlRepository.findByShortCode(shortCode);

        if(result.isEmpty()) {
            throw new ShortUrlNotFoundException("Short URL not found");
        }

        Url url = result.get();

        return url.getOriginalUrl();
    }



}
