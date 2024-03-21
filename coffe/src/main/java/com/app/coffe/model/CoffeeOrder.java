package com.app.coffe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CoffeeOrders")
public class CoffeeOrder {
    /**
     * id заказа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * название кофе
     */
    private String coffeeName;
    /**
     * количество порций
     */
    private int coffeeQuantaty;
    /**
     * стоимость заказа
     */
    private BigDecimal amount;
    /**
     * статус заказа
     */
    private OrderStatus orderStatus = OrderStatus.NEW;
    /**
     * имя пользователя
     */
    private String userName="user";
}
