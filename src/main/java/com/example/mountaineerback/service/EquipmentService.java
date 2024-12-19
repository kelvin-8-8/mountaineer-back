package com.example.mountaineerback.service;

import com.example.mountaineerback.model.dto.EquipmentDTO;
import com.example.mountaineerback.model.entity.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {

    List<EquipmentDTO> getAllEquipment();

    Optional<EquipmentDTO> getEquipmentByName(String name);

    Optional<EquipmentDTO> getEquipmentByRole(String role);

    Equipment addEquipment(EquipmentDTO equipmentDTO);
}
