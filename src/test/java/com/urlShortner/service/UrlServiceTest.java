package com.urlShortner.service;
import com.urlShortner.dto.ShortenUrlRequest;
import com.urlShortner.dto.ShortenUrlResponse;
import com.urlShortner.entity.Url;
import com.urlShortner.exception.ShortUrlNotFoundException;
import com.urlShortner.repository.UrlRepository;
import com.urlShortner.util.ShortCodeGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @Mock
    UrlRepository urlRepository;

    @Mock
    ShortCodeGenerator shortCodeGenerator;


    @Test
    void shouldCreateShortUrl() {
        UrlService urlService = new UrlService(
                urlRepository,
                shortCodeGenerator,
                "http://localhost:8080"
        );

        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setUrl("https://google.com");

        when(shortCodeGenerator.generateShortCode())
                .thenReturn("ABC123");

        when(urlRepository.existsByShortCode("ABC123"))
                .thenReturn(false);

        Url savedUrl = new Url();
        savedUrl.setOriginalUrl("https://google.com");
        savedUrl.setShortCode("ABC123");

        when(urlRepository.save(any(Url.class)))
                .thenReturn(savedUrl);

        ShortenUrlResponse response =
                urlService.createShortUrl(request);

        assertEquals(
                "http://localhost:8080/r/ABC123",
                response.getShortUrl()
        );

        verify(urlRepository).save(any(Url.class));
    }

    @Test
    void shouldGenerateNewCodeWhenCollisionOccurs() {

        ShortenUrlRequest request = new ShortenUrlRequest();
        request.setUrl("https://google.com");

        when(shortCodeGenerator.generateShortCode())
                .thenReturn("ABC123")
                .thenReturn("XYZ789");

        when(urlRepository.existsByShortCode("ABC123"))
                .thenReturn(true);

        when(urlRepository.existsByShortCode("XYZ789"))
                .thenReturn(false);

        Url savedUrl = new Url();
        savedUrl.setOriginalUrl("https://google.com");
        savedUrl.setShortCode("XYZ789");

        when(urlRepository.save(any(Url.class)))
                .thenReturn(savedUrl);

        // Act
        UrlService urlService = new UrlService(
                urlRepository,
                shortCodeGenerator,
                "http://localhost:8080"
        );

        ShortenUrlResponse response =
                urlService.createShortUrl(request);

        // Assert
        assertEquals(
                "http://localhost:8080/r/XYZ789",
                response.getShortUrl()
        );
    }
    
    @Test
    void shouldReturnOriginalUrl(){
        UrlService urlService = new UrlService(
                urlRepository,
                shortCodeGenerator,
                "http://localhost:8080"
        );

        Url url = new Url();
        url.setShortCode("ABC123");
        url.setOriginalUrl("https://google.com");

        when(urlRepository.findByShortCode("ABC123")).thenReturn(Optional.of(url));
        String result = urlService.getOriginalUrl("ABC123");

        assertEquals("https://google.com", result);

    }

    @Test
    void shouldThrowExceptionWhenShortCodeDoesNotExist() {

        UrlService urlService = new UrlService(
                urlRepository,
                shortCodeGenerator,
                "http://localhost:8080"
        );

        when(urlRepository.findByShortCode("XYZ999"))
                .thenReturn(Optional.empty());

        assertThrows(
                ShortUrlNotFoundException.class,
                () -> {
                    urlService.getOriginalUrl("XYZ999");
                }
        );
    }
}

