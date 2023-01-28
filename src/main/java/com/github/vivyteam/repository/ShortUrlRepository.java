package com.github.vivyteam.repository;

import com.github.vivyteam.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);

}