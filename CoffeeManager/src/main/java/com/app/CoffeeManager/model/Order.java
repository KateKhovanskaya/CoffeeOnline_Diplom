package com.app.CoffeeManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userOrderId;
    private String coffeeName;
    private int coffeeQuantaty;
    private BigDecimal amount;
    private OrderStatus orderStatus = OrderStatus.NEW;
    private String userName;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userOrderId=" + userOrderId +
                ", coffeeName='" + coffeeName + '\'' +
                ", coffeeQuantaty=" + coffeeQuantaty +
                ", amount=" + amount +
                ", orderStatus=" + orderStatus +
                ", userName='" + userName + '\'' +
                '}';
    }
}
