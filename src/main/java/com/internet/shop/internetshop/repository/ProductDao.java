package com.internet.shop.internetshop.repository;

import com.internet.shop.internetshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Optional<Product> findProductById(long id);

    Optional<Product> findProductBySku(String sku);

    List<Product> findAllProducts();

    Product createProduct(Product product);

    void removeProduct(long id);

//    Optional<Product> updateProduct(Product product);

    boolean existsBySku(String sku);

}
