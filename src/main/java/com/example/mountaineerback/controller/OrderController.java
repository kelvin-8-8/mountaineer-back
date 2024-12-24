package com.example.mountaineerback.controller;

import com.example.mountaineerback.model.dto.OrderDTO;
import com.example.mountaineerback.model.dto.UserDTO;
import com.example.mountaineerback.model.request.OrderRequest;
import com.example.mountaineerback.response.ApiResponse;
import com.example.mountaineerback.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /order
 * ----------------------------------
 * GET   /         查詢該使用者所有商品(多筆)
 * POST  /checkout 該使用者進行結帳
 * */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@RequestBody OrderRequest orderRequest, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        OrderDTO orderDTO = orderService.addOrder(userDTO.getId(),orderRequest);
        return ResponseEntity.ok(ApiResponse.success("新增訂單成功", orderDTO));
    }
}
