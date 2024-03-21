package com.app.paymentService.controllers;

import com.app.paymentService.dto.TransferRequest;
import com.app.paymentService.model.Account;
import com.app.paymentService.services.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paymentService")
public class TransferController {
    private final TransferService transferService;
    @GetMapping("/accounts")
    public List<Account> accounts(){
        return transferService.getAllAccounts();
    }
    @PostMapping("/transfer")
    public ResponseEntity<TransferRequest> transferMoney(@RequestBody TransferRequest request){
       try{transferService.transferMoney(
               request.getSenderAccountId(),
               request.getReceiverAccountId(),
               request.getAmount());
           ResponseEntity<TransferRequest> responseEntity = ResponseEntity.status(HttpStatus.OK).body(request);
           return responseEntity;
       } catch(Exception e){
           ResponseEntity<TransferRequest> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);
           return responseEntity;
       }
    }
}
