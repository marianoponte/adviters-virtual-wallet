package com.adviters.virtualwallet.service;

import com.adviters.virtualwallet.exception.BusinessException;
import com.adviters.virtualwallet.exception.ResourceNotFoundException;
import com.adviters.virtualwallet.model.*;
import com.adviters.virtualwallet.repository.TransactionRepository;
import com.adviters.virtualwallet.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyWalletService currencyWalletService;

    public Transaction getTransaction(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        } else
            return null;
    }

    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public Transaction createTransaction(TransactionPayload transactionPayload) {
        LocalDateTime timestamp = LocalDateTime.now();

        CurrencyWallet currencyWallet = currencyWalletService.getCurrencyWallet(transactionPayload.getIdCurrencyWallet());
        if (currencyWallet == null) {
            throw new ResourceNotFoundException("Id of the currency wallet is wrong");
        }
        if (transactionPayload.getTransactionType() == null) {
            throw new BusinessException("The transaction type cannot be null");
        }
        if (transactionPayload.getAmount() == null) {
            throw new BusinessException("The transaction amount cannot be null");
        }

        Transaction transaction = new Transaction(currencyWallet,
                transactionPayload.getTransactionType(),
                timestamp,
                transactionPayload.getAmount());

        Transaction transactionCreated = transactionRepository.save(transaction);

        //Actualizo el balance especificado en la transaccion
        updateCurrencyWallet(currencyWallet, transaction);

        return transactionCreated;
    }

    public void updateCurrencyWallet(CurrencyWallet currencyWallet, Transaction transaction) {
        if (Constants.TRANSACTION_TYPE_ACU.equals(transaction.getTransactionType().toUpperCase())) {
            currencyWallet.getAmount().add(transaction.getAmount());
            currencyWalletService.updateCurrencyWallet(currencyWallet);
        } else if (Constants.TRANSACTION_TYPE_EXT.equals(transaction.getTransactionType().toUpperCase()) {
            currencyWalletService.updateCurrencyWallet(currencyWallet);
        } else throw new BusinessException("Los valores correctos para el tipo de transaccion son: ACU y EXT");
    }
}
