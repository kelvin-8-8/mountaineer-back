package com.example.mountaineerback.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 商品數量
    private int quantity;

    // order_item 與 product 的關係是多對一關聯
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Equipment equipment;

    // order_item 與 order 的關係是多對一關聯
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
