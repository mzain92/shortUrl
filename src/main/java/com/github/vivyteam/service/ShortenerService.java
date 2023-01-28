package com.github.vivyteam.service;


import com.github.vivyteam.entity.ShortUrl;
import com.github.vivyteam.exception.ShortUrlNotFoundException;
import com.github.vivyteam.repository.ShortUrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenerService {
    @Autowired
    ShortUrlRepository shortUrlRepository;

    public ShortUrl createShortUrl(String url) {
        String shortCode = generateShortCode();
        while (shortUrlRepository.existsByShortCode(shortCode)) {
            shortCode = generateShortCode();
        }
        ShortUrl shortUrl = new ShortUrl(url, shortCode);
        return shortUrlRepository.save(shortUrl);
    }


    public String getFullUrl(String shortCode) {
        ShortUrl shortUrl = shortUrlRepository.findByShortCode(shortCode);


        if (shortUrl == null) {
            throw new ShortUrlNotFoundException("No URL found against this short code");
        }
        return shortUrl.getUrl();

    }


    private String generateShortCode() {
        // This method can generate a random string, hash the url, or use a UUID
        // For example, this generates a random 6-character alphanumeric string
        return RandomStringUtils.randomAlphanumeric(6);
    }


}
