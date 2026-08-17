package com.urlShortner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ShortenUrlRequest {
    @NotBlank
    @Pattern(
            regexp = "^(https?:\\/\\/)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$",
            message = "URL must start with http:// or https://"
    )
    private String url;

    public void setUrl(String url){
         this.url = url;
    }

    public  String getUrl(){
        return url;
    }

}
