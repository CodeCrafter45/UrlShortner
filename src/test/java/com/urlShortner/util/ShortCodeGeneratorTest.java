package com.urlShortner.util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ShortCodeGeneratorTest {
    @Test
    void shouldGenerateSixCharacterCode() {
       ShortCodeGenerator generator = new ShortCodeGenerator();

       String code = generator.generateShortCode();

        assertEquals(6, code.length());
    }
}
