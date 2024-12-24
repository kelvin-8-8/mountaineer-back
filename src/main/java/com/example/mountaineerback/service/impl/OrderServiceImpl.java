package com.example.mountaineerback.service.impl;

import com.example.mountaineerback.model.dto.OrderDTO;
import com.example.mountaineerback.model.dto.OrderItemDTO;
import com.example.mountaineerback.model.entity.Order;
import com.example.mountaineerback.model.entity.OrderItem;
import com.example.mountaineerback.model.entity.User;
import com.example.mountaineerback.model.request.OrderRequest;
import com.example.mountaineerback.repository.OrderItemRepository;
import com.example.mountaineerback.repository.OrderRepository;
import com.example.mountaineerback.repository.UserRepository;
import com.example.mountaineerback.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderDTO> findOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO addOrder(Long userId, OrderRequest orderRequest) {

        // 1. 找 User
        Optional<User> optUser = userRepository.findById(userId);
        if(optUser.isEmpty()) return null;

        // 2. 建立訂單 設定關聯關係
        Order order = new Order();
        order.setUser(optUser.get());

        // 3. TODO 非聯集操作的做法
        // 4.

        // 3.
        List<OrderItemDTO> items = orderRequest.getItems();
        List<OrderItem> orderItems = items.stream()
                .map(item -> {
                    OrderItem orderItem = modelMapper.map(item, OrderItem.class);
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());


        // 4. order
        order.setItems(orderItems);
        order.setStartDate(orderRequest.getStartDate());
        order.setDuration(orderRequest.getDuration());

        return modelMapper.map(order, OrderDTO.class);
    }
}
