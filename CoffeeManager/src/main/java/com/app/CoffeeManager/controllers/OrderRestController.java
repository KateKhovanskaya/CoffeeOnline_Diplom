package com.app.CoffeeManager.controllers;

import com.app.CoffeeManager.model.Coffee;
import com.app.CoffeeManager.model.Order;
import com.app.CoffeeManager.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("coffeeManagerService")
public class OrderRestController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public String loadOrder(@RequestBody String orderFromUser) throws JsonProcessingException {
        orderService.loadOrder(orderFromUser);
        return orderFromUser;
    }
}
