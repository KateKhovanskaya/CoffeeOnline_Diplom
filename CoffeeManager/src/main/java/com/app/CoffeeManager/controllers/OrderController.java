package com.app.CoffeeManager.controllers;

import com.app.CoffeeManager.model.Order;
import com.app.CoffeeManager.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("coffeeManagerService")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/orders-todo")
    public String showOrdersToDo(Model model){
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }
    @GetMapping("/update-order/{id}")
    String getOrderForUpdate(@PathVariable("id") Long id, Model model){
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "updateOrder";
    }
    @PostMapping("/update-order")
    String updateOrder(Order order) throws JsonProcessingException {
        orderService.updateOrder(order.getId(),order);

        return "redirect:/coffeeManagerService/orders-todo";
    }
}
