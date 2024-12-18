package com.example.mountaineerback.model.dto;

import com.example.mountaineerback.model.entity.Order;
import com.example.mountaineerback.model.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String trueName;
    private String email;
    private String role;
    private LocalDateTime createdAt;
    private List<Order> orders;
    private List<Trip> createdTrips;
    private Set<Trip> joinedTrips;
}
