package com.internet.shop.internetshop.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        createAt = LocalDateTime.now();
        updateAt = createAt;
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = LocalDateTime.now();
    }

}
