package com.app.CoffeeManager.services;

import com.app.CoffeeManager.dto.UpdateOrderRequest;
import com.app.CoffeeManager.model.Order;
import com.app.CoffeeManager.repositoryes.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }
    public Order getOrderById(Long id){
        return orderRepository.findById(id).get();
    }
    public Order updateOrder(Long id, Order order) throws JsonProcessingException {
        Order updatedOrder = orderRepository.findById(id).get();
        updatedOrder.setUserOrderId(order.getUserOrderId());
        updatedOrder.setUserName(order.getUserName());
        updatedOrder.setCoffeeName(order.getCoffeeName());
        updatedOrder.setCoffeeQuantaty(order.getCoffeeQuantaty());
        updatedOrder.setAmount(order.getAmount());
        updatedOrder.setOrderStatus(order.getOrderStatus());
        // Отправляем уведомление о смене статауса заказа
        sendUpdateRequest(updatedOrder);
        return orderRepository.save(updatedOrder);
    }

    public Order loadOrder(String orderFromUser) throws JsonProcessingException {
        JsonNode receivedOrder = objectMapper.readTree(orderFromUser);
        Order order = new Order();
        order.setUserOrderId(receivedOrder.path("id").asLong());
        order.setUserName(receivedOrder.path("userName").asText());
        order.setCoffeeName(receivedOrder.path("coffeeName").asText());
        order.setCoffeeQuantaty(receivedOrder.path("coffeeQuantaty").asInt());
        order.setAmount(new BigDecimal(receivedOrder.path("amount").asDouble()));
        return orderRepository.save(order);
    }

    public String sendUpdateRequest(Order order) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        UpdateOrderRequest updatingRequest = new UpdateOrderRequest(order.getUserOrderId(), order.getOrderStatus());
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(updatingRequest);
        HttpEntity<String> requestToClient = new HttpEntity<String>(json, headers);
        String answerFromClient = restTemplate.postForObject("http://localhost:8100/coffee-online/updateCoffeeOrder", requestToClient, String.class);
        return answerFromClient;
    }
}
