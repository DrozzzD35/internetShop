package com.internet.shop.internetshop.dto;

import com.internet.shop.internetshop.converter.ProductAttributes;
import com.internet.shop.internetshop.model.Money;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.awt.*;

@Data
public class ProductDto {

    private ProductAttributes attributes;

    @NotBlank
    @Size(max = 50)
    private String name;

    private String description;

    @NotNull
    private Money price;

    @NotNull
    private Integer stock;

    @NotNull
    private String sku;


}
