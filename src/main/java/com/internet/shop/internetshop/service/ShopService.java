package com.internet.shop.internetshop.service;

import com.internet.shop.internetshop.enums.Status;
import com.internet.shop.internetshop.model.Order;
import com.internet.shop.internetshop.model.Product;
import com.internet.shop.internetshop.repository.OrderDao;
import com.internet.shop.internetshop.repository.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {
    private final ProductDao productDao;
    private final OrderDao orderDao;

    public Order getOrderById(long id) {
        return orderDao.findOrderById(id)
                .orElseThrow(() -> new RuntimeException("Ошибка поиска заказа по id"));
    }

    public List<Order> getAllOrders() {
        return orderDao.findAllOrders();
    }

    public Order createOrder(Order order, Product product) {
        if (product.getStock() <= 0) {
            throw new RuntimeException("Товара нет в наличии");
        }

        order.setStatus(Status.ACTIVE);
        order.setTotal(product.getPrice());
        product.setStock(product.getStock() - 1);
        Product updatedProduct = productDao.updateProduct(product)
                .orElseThrow(() -> new RuntimeException("Ошибка при обновлении"));
        Order createdOrder = orderDao.createOrder(order);
        log.info("Заказ создан id - {} и товар обновлён в {}",
                createdOrder.getId(), updatedProduct.getUpdatedAt());
        return createdOrder;
    }

    public void removeOrder(long id) {
        orderDao.removeOrder(id);
        log.info("Заказ удалён");
    }

    public Order updateOrder(Order order) {
        Order updatedOrder = orderDao.updateOrder(order)
                .orElseThrow(() -> new RuntimeException("Ошибка при обновлении заказа"));
        log.info("Заказ обновлён");
        return updatedOrder;
    }

    public Product getProductById(long id) {
        return productDao.findProductById(id)
                .orElseThrow(() -> new RuntimeException("Ошибка поиска продукта по id"));
    }

    public Product getProductBySku(String sku) {
        return productDao.findProductBySku(sku)
                .orElseThrow(() -> new RuntimeException("Ошибка поиска продукта по артикулу"));
    }

    public List<Product> getAllProducts() {
        return productDao.findAllProducts();
    }

    public Product createProduct(Product product) {
        Product createdProduct = productDao.createProduct(product);
        log.info("Товар создан");
        return createdProduct;
    }

    public void removeProduct(long id) {
        productDao.removeProduct(id);
        log.info("Товар удалён");
    }

    public Product updateProduct(Product product) {
        Product updatedProduct = productDao.updateProduct(product)
                .orElseThrow(() -> new RuntimeException("Ошибка при обновлении товара"));
        log.info("Товар обновлён");
        return updatedProduct;
    }


}
