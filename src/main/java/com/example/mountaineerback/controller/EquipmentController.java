package com.example.mountaineerback.controller;


import com.example.mountaineerback.model.dto.EquipmentDTO;
import com.example.mountaineerback.model.enums.EQUIPMENT_TYPE;
import com.example.mountaineerback.response.ApiResponse;
import com.example.mountaineerback.response.EquipmentResponse;
import com.example.mountaineerback.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 * WEB REST API
 */
@Slf4j
@RestController
@RequestMapping("/equip")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EquipmentDTO>>> getEquipmentAll() {
        List<EquipmentDTO> equipmentDTO = equipmentService.getAllEquipment();
        return ResponseEntity.ok(ApiResponse.success("找全部裝備", equipmentDTO));
    }

    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse<List<EquipmentDTO>>> getEquipmentType(@PathVariable String type) {
        //將傳入的 String 轉換成 Enum type
        EQUIPMENT_TYPE equipmentType = EQUIPMENT_TYPE.valueOf(type.toUpperCase());
        List<EquipmentDTO> equipmentDTO = equipmentService.getEquipmentByType(equipmentType);

        return ResponseEntity.ok(ApiResponse.success("利用type更新裝備", equipmentDTO));
    }
}
