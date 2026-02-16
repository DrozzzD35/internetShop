package com.internet.shop.internetshop.repository;

import com.internet.shop.internetshop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Optional<Order> findOrderById(long id);

    List<Order> findAllOrders();

    Order createOrder(Order order);

    void removeOrder(long id);

    Optional<Order> updateOrder(Order order);

}
