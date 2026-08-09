package com.urlShortner.dto;

import jakarta.validation.constraints.NotBlank;

public class ShortenUrlRequest {
    @NotBlank
    private String url;

    public void setUrl(String url){
         this.url = url;
    }

    public  String getUrl(){
        return url;
    }

}
