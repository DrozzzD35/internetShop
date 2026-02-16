package com.internet.shop.internetshop.controller;


import com.internet.shop.internetshop.dto.OrderProductDTO;
import com.internet.shop.internetshop.model.Order;
import com.internet.shop.internetshop.model.Product;
import com.internet.shop.internetshop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ShopController {
    private final ShopService service;

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable long id) {
        return service.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody OrderProductDTO orderProductDTO) {
        Order order = orderProductDTO.getOrder();
        Product product = orderProductDTO.getProduct();
        return service.createOrder(order, product);
    }

    @DeleteMapping("/orders/{id}")
    public void removeOrder(@PathVariable long id) {
        service.removeOrder(id);
    }

    @PutMapping("/orders")
    public Order updateOrder(@RequestBody Order order) {
        return service.updateOrder(order);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) {
        return service.getProductById(id);
    }

    @GetMapping("/products/sku/{sku}")
    public Product getProductBySku(@PathVariable String sku) {
        return service.getProductBySku(sku);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void removeProduct(@PathVariable long id) {
        service.removeProduct(id);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

}
