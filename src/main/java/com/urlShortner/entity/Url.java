package com.urlShortner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String originalUrl;
    @Getter
    @Setter
    @Column(unique = true)
    private String shortCode;

}
