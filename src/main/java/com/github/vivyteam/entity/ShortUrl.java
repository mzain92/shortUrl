package com.github.vivyteam.entity;

import javax.persistence.*;

@Entity
public class ShortUrl {
    public ShortUrl(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public ShortUrl(String url, String shortCode) {
        this.url = url;
        this.shortCode = shortCode;
    }

    private String url;

    public ShortUrl(Long id, String url, String shortCode) {
        this.id = id;
        this.url = url;
        this.shortCode = shortCode;
    }
    @Column(unique = true)
    private String shortCode;
    // getters and setters
}