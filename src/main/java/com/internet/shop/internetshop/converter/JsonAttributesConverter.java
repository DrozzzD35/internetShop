package com.internet.shop.internetshop.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.databind.ObjectMapper;

@Converter
public class JsonAttributesConverter implements AttributeConverter<ProductAttributes, String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ProductAttributes productAttributes) {
        if (productAttributes == null) {
            return null;
        }

        try {
            return mapper.writeValueAsString(productAttributes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при превращении объекта в json");
        }
    }

    @Override
    public ProductAttributes convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        try {
            return mapper.readValue(s, ProductAttributes.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при превращении json в объект");
        }
    }
}
