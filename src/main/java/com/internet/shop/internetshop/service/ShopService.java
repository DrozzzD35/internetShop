package com.internet.shop.internetshop.service;

import com.internet.shop.internetshop.model.Order;
import com.internet.shop.internetshop.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final Product product;
    private final Order order;




}
