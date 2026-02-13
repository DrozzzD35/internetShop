package com.internet.shop.internetshop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(
            nullable = false,
            length = 50
    )
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Embedded
    private Money price;

    private int stock;

    @Column(
            nullable = false,
            unique = true
    )
    private String sku;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;


}
