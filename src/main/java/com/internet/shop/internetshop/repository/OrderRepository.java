package com.internet.shop.internetshop.repository;

import com.internet.shop.internetshop.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements OrderDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Order> findOrderById(long id) {
        Order order = manager.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return manager.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    @Override
    public Order createOrder(Order order) {
        if (order.getId() != 0) {
            throw new RuntimeException("Ошибка при создании заказа");
        }
        manager.persist(order);
        return order;
    }

    @Override
    public void removeOrder(long id) {
        Optional<Order> optionalOrder = findOrderById(id);
        optionalOrder.ifPresent(manager::remove);
    }

//    @Override
//    public Optional<Order> updateOrder(Order order) {
//        if (order.getId() == 0) {
//            throw new RuntimeException("Ошибка при обновлении заказа");
//        }
//        Order updatedOrder = manager.merge(order);
//        return Optional.of(updatedOrder);
//    }
}
