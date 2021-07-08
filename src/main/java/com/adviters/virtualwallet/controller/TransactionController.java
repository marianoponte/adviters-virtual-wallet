package com.adviters.virtualwallet.controller;

import com.adviters.virtualwallet.exception.BusinessException;
import com.adviters.virtualwallet.exception.ResourceNotFoundException;
import com.adviters.virtualwallet.model.Transaction;
import com.adviters.virtualwallet.model.TransactionPayload;
import com.adviters.virtualwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransaction(id);
        if (transaction != null) {
            return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Transaction not found!");
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = transactionService.getTransactions();
        return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionPayload transactionPayload) {
        Transaction transaction = transactionService.createTransaction(transactionPayload);
        if (transaction != null) {
            return new ResponseEntity<Transaction>(transaction, HttpStatus.CREATED);
        }
        else {
            throw new BusinessException("Something went wrong in the creation of the transaction :(");
        }
    }
}