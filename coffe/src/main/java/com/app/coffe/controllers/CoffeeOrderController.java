package com.app.coffe.controllers;

import com.app.coffe.model.CoffeeOrder;
import com.app.coffe.services.CoffeeOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("coffee-online")
public class CoffeeOrderController {
    private final CoffeeOrderService coffeeOrderService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
//    @PostMapping("/login")
//    public String loging()
    @GetMapping("/")
    public String mainPage(){
        return "orders";
    }
    @GetMapping("/orders")
    public String orderPage(Model model){
        List<CoffeeOrder> orderList = coffeeOrderService.getAllCoffeeOrders();
        model.addAttribute("orders", orderList);
        return "orders";
    }
    @GetMapping("/new-order")
    public String newOrder(CoffeeOrder coffeeOrder){
        return "new-order";
    }
    @PostMapping("/new-order")
    public String addCoffeeOrder(CoffeeOrder coffeeOrder){
        coffeeOrderService.saveCoffeeOrder(coffeeOrder);
        return "redirect:/coffee-online/orders";
    }
    @GetMapping("/order-paid/{id}")
    String getOneOrder(@PathVariable("id") Long id, Model model){
        CoffeeOrder coffeeOrder = coffeeOrderService.getOneOrder(id);
        model.addAttribute("order", coffeeOrder);

        return "order-paid";
    }
    @PostMapping("/order-paid")
        public String paidOrder(CoffeeOrder coffeeOrder) throws JsonProcessingException {
            coffeeOrderService.paidOrder(coffeeOrder);
            return "redirect:/coffee-online/orders";
        }
    @GetMapping("/order-delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id){
        coffeeOrderService.deleteOrderById(id);
        return "redirect:/coffee-online/orders";
    }

}
