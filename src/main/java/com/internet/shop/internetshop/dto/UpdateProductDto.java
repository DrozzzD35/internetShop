package com.internet.shop.internetshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProductDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    private String description;

}
