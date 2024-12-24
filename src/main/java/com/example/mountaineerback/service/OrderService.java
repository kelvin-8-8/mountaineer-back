package com.example.mountaineerback.service;

import com.example.mountaineerback.model.dto.OrderDTO;
import com.example.mountaineerback.model.dto.OrderItemDTO;
import com.example.mountaineerback.model.request.OrderRequest;

import java.util.List;

public interface OrderService {

    // 根據使用者id找到
    public List<OrderDTO> findOrderByUserId(Long userId);

    public OrderDTO addOrder(Long userid, OrderRequest orderRequest);
}
