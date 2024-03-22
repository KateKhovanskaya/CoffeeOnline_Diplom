package com.app.CoffeeManager.dto;


import com.app.CoffeeManager.model.Coffee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class CoffeeList {
    private List<Coffee> coffeeList;
}
