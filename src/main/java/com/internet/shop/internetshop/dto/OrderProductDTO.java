package com.internet.shop.internetshop.dto;

import com.internet.shop.internetshop.model.Order;
import com.internet.shop.internetshop.model.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderProductDTO {
    private Order order;
    private Product product;

}
