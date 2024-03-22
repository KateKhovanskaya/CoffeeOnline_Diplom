package com.app.CoffeeManager.services;

import com.app.CoffeeManager.dto.CoffeeList;
import com.app.CoffeeManager.model.Coffee;
import com.app.CoffeeManager.repositoryes.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public void addDefaultCoffee(){
        if(coffeeRepository.findAll().isEmpty()) {
            Coffee coffee1 = new Coffee();
            coffee1.setCoffeeName("Американо");
            coffee1.setAmount(new BigDecimal(2.3));
            coffeeRepository.save(coffee1);
            Coffee coffee2 = new Coffee();
            coffee2.setCoffeeName("Капучино");
            coffee2.setAmount(new BigDecimal(3.5));
            coffeeRepository.save(coffee2);
            Coffee coffee3 = new Coffee();
            coffee3.setCoffeeName("Латте");
            coffee3.setAmount(new BigDecimal(4.2));
            coffeeRepository.save(coffee3);
        }
    }
    public CoffeeList getAllCoffee(){
        addDefaultCoffee();
        CoffeeList coffeeList = new CoffeeList(coffeeRepository.findAll());
        return coffeeList;
    }
}
