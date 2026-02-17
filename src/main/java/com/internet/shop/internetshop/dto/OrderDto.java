package com.internet.shop.internetshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {

    @NotNull
    private Long productId;
}
