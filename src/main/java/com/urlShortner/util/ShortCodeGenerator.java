package com.urlShortner.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShortCodeGenerator {
    private final Random random = new Random();
    private final String allowedChars =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

     public String generateShortCode(){
         StringBuilder code = new StringBuilder();



         for(int i=0; i<6; i++){
             int index =  random.nextInt(allowedChars.length());
             char ch = allowedChars.charAt(index);
             code.append(ch);
         }

         return code.toString();
     }

}
