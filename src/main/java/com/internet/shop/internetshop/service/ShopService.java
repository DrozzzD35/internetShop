package com.internet.shop.internetshop.service;

import com.internet.shop.internetshop.dto.OrderDto;
import com.internet.shop.internetshop.dto.ProductDto;
import com.internet.shop.internetshop.dto.UpdateOrderDto;
import com.internet.shop.internetshop.dto.UpdateProductDto;
import com.internet.shop.internetshop.enums.Status;
import com.internet.shop.internetshop.model.Order;
import com.internet.shop.internetshop.model.Product;
import com.internet.shop.internetshop.repository.OrderDao;
import com.internet.shop.internetshop.repository.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
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

    @Transactional
    public Order createOrder(OrderDto orderDto) {
        Product product = productDao.findProductById(orderDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));

        if (product.getStock() <= 0) {
            throw new RuntimeException("Товара нет в наличии");
        }

        Order order = new Order();
        order.setTotal(product.getPrice());
        product.setStock(product.getStock() - 1);
        Order createdOrder = orderDao.createOrder(order);

        log.info("Заказ создан id - {}, id товара - {}",
                createdOrder.getId(), product.getId());

        return createdOrder;
    }

    @Transactional
    public void removeOrder(long id) {
        Order order = orderDao.findOrderById(id)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        order.setDeletedAt(LocalDateTime.now());
        order.setStatus(Status.ARCHIVED);
        log.info("Заказ с id - {} отправлен в архив (удалён)", order.getId());
    }

    @Transactional
    public Order updateOrder(UpdateOrderDto orderDto) {
        Order order = orderDao.findOrderById(orderDto.getId())
                .orElseThrow(() -> new RuntimeException("Ошибка при поиске заказа"));

        if (order.getStatus() != orderDto.getStatus()) {
            order.setStatus(orderDto.getStatus());
            log.info("Заказ обновлён");
        }

        return order;
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

    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setAttributes(productDto.getAttributes());
        if (productDao.existsBySku(productDto.getSku())) {
            throw new RuntimeException("Продукт с таким артикулом уже существует");
        }
        product.setSku(productDto.getSku());

        Product createdProduct = productDao.createProduct(product);
        log.info("Товар создан");
        return createdProduct;
    }

    @Transactional
    public void removeProduct(long id) {
        Product product = productDao.findProductById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));

        product.setDeletedAt(LocalDateTime.now());
        product.setStatus(Status.ARCHIVED);
        log.info("Товар с id - {} отправлен в архив (удалён)", product.getId());
    }

    @Transactional
    public Product updateProduct(UpdateProductDto productDto) {
        Product productInBase = productDao.findProductById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Ошибка при обновлении товара"));

        if (!Objects.equals(productInBase.getName(), productDto.getName())) {
            productInBase.setName(productDto.getName());
        }
        if (!Objects.equals(productInBase.getDescription(), productDto.getDescription())) {
            productInBase.setDescription(productDto.getDescription());
        }

        log.info("Товар обновлён");
        return productInBase;
    }


}
