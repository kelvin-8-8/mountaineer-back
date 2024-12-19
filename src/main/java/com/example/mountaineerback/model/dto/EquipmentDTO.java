package com.example.mountaineerback.model.dto;

import com.example.mountaineerback.model.entity.EquipmentImage;
import com.example.mountaineerback.model.entity.OrderItem;
import com.example.mountaineerback.model.enums.EQUIPMENT_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {
    private Long id;
    private String name;
    private Integer price;
    private EQUIPMENT_TYPE type;
    private String description;
    private List<OrderItem> orderItems;
    private EquipmentImage equipmentImage;
}