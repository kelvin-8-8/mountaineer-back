package com.example.mountaineerback.model.entity;

import com.example.mountaineerback.model.enums.ORDER_STATUS;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ORDER_STATUS status;

    // order 與 user 的關係是多對一關聯
    // 創建user_id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // order 與 order_item 的關係是一對多
    // FetchType.EAGER 在查找 order 的同時一併找 OrderItem
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> items;

}
