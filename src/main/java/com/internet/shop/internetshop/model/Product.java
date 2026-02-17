package com.internet.shop.internetshop.model;

import com.internet.shop.internetshop.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "products")
@SQLRestriction("deleted_at IS NULL")
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
    private Status status = Status.ACTIVE;


}
