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
@Table(name = "orders")
@SQLRestriction("deleted_at IS NULL")
public class Order extends BaseEntity {

    @Embedded
    private Money total;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Version
    private Integer version;

}

