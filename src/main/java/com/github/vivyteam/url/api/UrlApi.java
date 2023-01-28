package com.github.vivyteam.url.api;

import com.github.vivyteam.entity.ShortUrl;
import com.github.vivyteam.repository.ShortUrlRepository;
import com.github.vivyteam.service.ShortenerService;
import com.github.vivyteam.url.api.contract.FullUrl;
import com.github.vivyteam.url.api.contract.ShortenedUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import java.net.URI;


@RestController
public class UrlApi {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    ShortenerService shortenerService;

    @GetMapping("{protocol}://{url}/short")
    public Mono<ShortenedUrl> shortUrl(@PathVariable final String url,@PathVariable final String protocol) {
        // Generate a unique short code for the URL
       ShortUrl shortUrl = shortenerService.createShortUrl(protocol+"://"+url);
        return Mono.just(new ShortenedUrl(shortUrl.getShortCode()));

    }

    @GetMapping("/{shortenedUrl}/full")
    public Mono<FullUrl> getFullUrl(@PathVariable final String shortenedUrl) {
        return Mono.just(new FullUrl(shortenerService.getFullUrl(shortenedUrl)));
    }

    @GetMapping("/{shortenedUrl}")
    public ResponseEntity redirectActualUrlRedirection(@PathVariable final String shortenedUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(shortenerService.getFullUrl(shortenedUrl)));
        return new ResponseEntity<>(headers, HttpStatus.TEMPORARY_REDIRECT);

    }


}
