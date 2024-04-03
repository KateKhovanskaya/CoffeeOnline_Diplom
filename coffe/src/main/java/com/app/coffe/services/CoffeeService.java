package com.app.coffe.services;

import com.app.coffe.dto.CoffeeList;
import com.app.coffe.model.Coffee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    @Autowired
    private RestTemplate restTemplate;
    public List<Coffee> loadCoffee(){
        List<Coffee> coffeeList = restTemplate
                .getForObject("http://localhost:8082/coffeeManagerService/coffee", CoffeeList.class)
                .getCoffeeList();
        return coffeeList;
    }
}
