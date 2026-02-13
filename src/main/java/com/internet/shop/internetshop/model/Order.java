package com.internet.shop.internetshop.model;

import com.internet.shop.internetshop.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Embedded
    private Money total;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Version
    private Integer version;

}

