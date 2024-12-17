package com.example.mountaineerback.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`equipment`")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    private String description;

    @OneToMany(mappedBy = "equipment")
    private List<OrderItem> orderItems;

    // 與 ProductImage 的一對一關聯 (單向)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_image_id")
    private EquipmentImage equipmentImage;
}