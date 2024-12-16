package com.example.mountaineerback.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // order 與 user 的關係是多對一關聯
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // order 與 order_item 的關係是一對多
    // FetchType.EAGER 在查找 order 的同時一併找 OrderItem
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> items;

}
