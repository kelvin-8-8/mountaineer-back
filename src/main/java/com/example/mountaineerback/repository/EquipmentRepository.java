package com.example.mountaineerback.repository;

import com.example.mountaineerback.model.enums.EQUIPMENT_TYPE;
import com.example.mountaineerback.model.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByType(EQUIPMENT_TYPE type);
}
