package com.internet.shop.internetshop.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Converter
public class SkuConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String s) {
        if (s == null) {
            return null;
        }

        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        return new String(Base64.getDecoder().decode(s));
    }
}
