package com.example.mountaineerback.service.impl;

import com.example.mountaineerback.model.dto.EquipmentDTO;
import com.example.mountaineerback.model.entity.Equipment;
import com.example.mountaineerback.model.entity.EquipmentImage;
import com.example.mountaineerback.model.enums.EQUIPMENT_TYPE;
import com.example.mountaineerback.model.request.EquipmentRequest;
import com.example.mountaineerback.repository.EquipmentImageRepository;
import com.example.mountaineerback.repository.EquipmentRepository;
import com.example.mountaineerback.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentImageRepository equipmentImageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentRepository.findAll().stream()
                .map(equipment -> {
                    EquipmentDTO equipmentDTO = modelMapper.map(equipment, EquipmentDTO.class);
                    if (equipment.getEquipmentImage() != null) {
                        equipmentDTO.setUrl(equipment.getEquipmentImage().getUrl());
                    }
                    return equipmentDTO;} )
                .collect(Collectors.toList());
    }

//    @Override
//    Optional<EquipmentDTO> getEquipmentByName(String name);
//
    // TODO 還沒測試過
    @Override
    public List<EquipmentDTO> getEquipmentByType(EQUIPMENT_TYPE type) {
        return equipmentRepository.findByType(type).stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentDTO.class))
                .collect(Collectors.toList());
    }

    //
    @Override
    public EquipmentDTO addEquipment(EquipmentRequest equipmentRequest) {
        // DTO 轉 entity
        Equipment equipment = modelMapper.map(equipmentRequest, Equipment.class);

        // 跟 image 關聯
        EquipmentImage equipmentImage = new EquipmentImage();
        equipmentImage.setUrl(equipmentRequest.getUrl());
        System.out.println(equipmentImage);
        equipment.setEquipmentImage(equipmentImage);

        equipment = equipmentRepository.save(equipment);

        EquipmentDTO equipmentDTO = modelMapper.map(equipment, EquipmentDTO.class);
        equipmentDTO.setUrl(equipmentRequest.getUrl());
        return equipmentDTO;
    }
}
