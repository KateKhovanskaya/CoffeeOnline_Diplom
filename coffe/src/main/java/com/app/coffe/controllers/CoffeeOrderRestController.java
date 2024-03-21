package com.app.coffe.controllers;

import com.app.coffe.services.CoffeeOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("coffee-online")
public class CoffeeOrderRestController {
    private final CoffeeOrderService coffeeOrderService;
    @PostMapping("/updateCoffeeOrder")
    public String updateCoffeeOrder(@RequestBody String updatingRequest) throws JsonProcessingException {
//        System.out.println("Controller updatingRequest: " + updatingRequest);
        coffeeOrderService.updateOrder(updatingRequest);
        return updatingRequest;
    }
}
