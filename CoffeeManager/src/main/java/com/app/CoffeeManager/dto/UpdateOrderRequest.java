package com.app.CoffeeManager.dto;

import com.app.CoffeeManager.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateOrderRequest {
    private Long id;
    private OrderStatus orderStatus;
}
