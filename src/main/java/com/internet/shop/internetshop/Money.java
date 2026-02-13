package com.internet.shop.internetshop;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class Money {
    private BigDecimal amount;
    private String currency;

}
