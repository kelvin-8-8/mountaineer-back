package com.example.mountaineerback.service;

import com.example.mountaineerback.model.dto.OrderDTO;
import com.example.mountaineerback.model.dto.OrderItemDTO;

import java.util.List;

public interface OrderService {

    public List<OrderDTO> findOrderByUserId(Long userid);

    public OrderDTO addOrder(Long userid, List<OrderItemDTO> items);
}
