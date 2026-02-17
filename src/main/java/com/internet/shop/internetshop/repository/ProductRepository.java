package com.internet.shop.internetshop.repository;

import com.internet.shop.internetshop.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ProductRepository implements ProductDao {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Optional<Product> findProductById(long id) {
        Product product = manager.find(Product.class, id);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> findProductBySku(String sku) {
        return manager.createQuery("SELECT p FROM Product p WHERE p.sku = :sku", Product.class)
                .setParameter("sku", sku)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Product> findAllProducts() {
        return manager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getId() != 0) {
            throw new RuntimeException("Ошибка при создании, продукт уже существует");
        }
        manager.persist(product);
        return product;
    }

    @Override
    public void removeProduct(long id) {
        Optional<Product> optionalProduct = findProductById(id);
        optionalProduct.ifPresent(manager::remove);
    }

    @Override
    public boolean existsBySku(String sku) {
        Long result = manager.createQuery("SELECT count(p) FROM Product p WHERE p.sku = :sku", Long.class)
                .setParameter("sku", sku)
                .getSingleResult();

        return result > 0;
    }

//    @Override
//    public Optional<Product> updateProduct(Product product) {
//        if (product.getId() == 0) {
//            throw new RuntimeException("Ошибка при обновлении, продукта не существует");
//        }
//        Product updatedProduct = manager.merge(product);
//        return Optional.of(updatedProduct);
//    }


}
