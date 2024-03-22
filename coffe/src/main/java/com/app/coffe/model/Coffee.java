package com.app.coffe.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Coffee {
    private Long id;
    private String coffeeName;
    private BigDecimal amount;
}
