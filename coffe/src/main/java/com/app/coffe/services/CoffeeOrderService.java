package com.app.coffe.services;

import com.app.coffe.dto.TransferRequest;
import com.app.coffe.model.CoffeeOrder;
import com.app.coffe.model.OrderStatus;
import com.app.coffe.repositoreis.CoffeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CoffeeOrderService {
    private final CoffeeRepository coffeeRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public List<CoffeeOrder> getAllCoffeeOrders(){
        return StreamSupport.stream(coffeeRepository.findAll().spliterator(), false)
                .toList();
    }
    public CoffeeOrder saveCoffeeOrder(CoffeeOrder coffeeOrder){
        return coffeeRepository.save(coffeeOrder);
    }
    public CoffeeOrder updateOrderStatus(CoffeeOrder coffeeOrder, OrderStatus status){
        coffeeOrder.setOrderStatus(status);
        return coffeeRepository.save(coffeeOrder);
    }
    public CoffeeOrder getOneOrder(Long id){
        return coffeeRepository.findById(id).get();
    }
    public CoffeeOrder updateOrder(Long id, CoffeeOrder coffeeOrder){
        coffeeOrder.setId(id);
        CoffeeOrder updatedOrder = coffeeRepository.findById(id).get();
        updatedOrder.setCoffeeName(coffeeOrder.getCoffeeName());
        updatedOrder.setCoffeeQuantaty(coffeeOrder.getCoffeeQuantaty());
        updatedOrder.setAmount(coffeeOrder.getAmount());
        updatedOrder.setUserName(coffeeOrder.getUserName());
        updatedOrder.setOrderStatus(coffeeOrder.getOrderStatus());
        return coffeeRepository.save(updatedOrder);
    }
    public CoffeeOrder updateOrder(String updatingRequest) throws JsonProcessingException {
        JsonNode receivedRequest = objectMapper.readTree(updatingRequest);
        Long orderId = receivedRequest.path("id").asLong();
        CoffeeOrder updatingOrder = coffeeRepository.findById(orderId).get();
        updatingOrder.setOrderStatus(OrderStatus.valueOf(receivedRequest.path("orderStatus").asText()));
        return updateOrder(orderId, updatingOrder);
    }
    public void deleteOrderById(Long id){
        coffeeRepository.deleteById(id);
    }
    public void paidOrder(CoffeeOrder coffeeOrder) throws JsonProcessingException {
        TransferRequest paymentRequest = new TransferRequest();
        paymentRequest.setSenderAccountId(1);
        paymentRequest.setReceiverAccountId(2);
        paymentRequest.setAmount(coffeeOrder.getAmount());
        ResponseEntity<TransferRequest> entity = restTemplate.postForEntity("http://localhost:8100/paymentService/transfer", paymentRequest, TransferRequest.class);
        if(entity.getStatusCode() == HttpStatusCode.valueOf(200)){
            coffeeOrder.setOrderStatus(OrderStatus.PAID);
            updateOrder(coffeeOrder.getId(), coffeeOrder);
            sendOrderToCafe(coffeeOrder);
        }
    }
    public String sendOrderToCafe(CoffeeOrder coffeeOrder) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String jsonStr = ow.writeValueAsString(coffeeOrder);
        HttpEntity<String> requestToCafe = new HttpEntity<String>(jsonStr, headers);
        String answerFromCafe = restTemplate
                .postForObject("http://localhost:8100/coffeeManagerService/orders",
                        requestToCafe,
                        String.class);
        return answerFromCafe;
    }
}
