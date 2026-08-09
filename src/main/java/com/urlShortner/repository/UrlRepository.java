package com.urlShortner.repository;

import com.urlShortner.entity.Url;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long> {

    boolean existsByShortCode(String shortCode);
    Optional<Url> findByShortCode(String shortCode);
}
