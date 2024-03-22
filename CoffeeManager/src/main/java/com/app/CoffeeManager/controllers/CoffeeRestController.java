package com.app.CoffeeManager.controllers;

import com.app.CoffeeManager.dto.CoffeeList;
import com.app.CoffeeManager.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("coffeeManagerService")
public class CoffeeRestController {
    private final CoffeeService coffeeService;
    @GetMapping("/coffee")
    public CoffeeList coffeeTypes(){
        CoffeeList coffeeList = coffeeService.getAllCoffee();
        return coffeeList;
    }
}
