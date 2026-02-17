package com.internet.shop.internetshop.dto;

import com.internet.shop.internetshop.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderDto {

    @NotNull
    private Long id;

    private Status status;

}
