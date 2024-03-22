package com.app.coffe.dto;

import com.app.coffe.model.Coffee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeList {
    private List<Coffee> coffeeList;
}
