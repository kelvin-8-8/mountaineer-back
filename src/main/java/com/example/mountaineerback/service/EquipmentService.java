package com.example.mountaineerback.service;

import com.example.mountaineerback.model.dto.EquipmentDTO;
import com.example.mountaineerback.model.entity.Equipment;
import com.example.mountaineerback.model.enums.EQUIPMENT_TYPE;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {

    List<EquipmentDTO> getAllEquipment();

//    Optional<EquipmentDTO> getEquipmentByName(String name);

    List<EquipmentDTO> getEquipmentByType(EQUIPMENT_TYPE type);

//    Equipment addEquipment(EquipmentDTO equipmentDTO);
}
