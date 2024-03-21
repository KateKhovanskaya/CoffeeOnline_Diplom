package com.app.paymentService.services;

import com.app.paymentService.model.Account;
import com.app.paymentService.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferService {
    private final AccountRepository accountRepository;
    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender, senderNewAmount);
//        throw new RuntimeException("Oh no! Something went wrong!");
         accountRepository.changeAmount(idReceiver, receiverNewAmount);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
