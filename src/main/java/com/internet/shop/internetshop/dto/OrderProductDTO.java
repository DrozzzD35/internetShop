package com.internet.shop.internetshop.dto;

import com.internet.shop.internetshop.model.Order;
import com.internet.shop.internetshop.model.Product;
import lombok.Data;

@Data
public class OrderProductDTO {
    private Order order;
    private Product product;

}
