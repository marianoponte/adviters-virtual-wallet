package com.adviters.virtualwallet.controller;

import com.adviters.virtualwallet.exception.BusinessException;
import com.adviters.virtualwallet.exception.ResourceNotFoundException;
import com.adviters.virtualwallet.model.CurrencyWallet;
import com.adviters.virtualwallet.model.Wallet;
import com.adviters.virtualwallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/wallet/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable Long id) {
        Wallet wallet = walletService.getWallet(id);
        if (wallet != null) {
            return new ResponseEntity<Wallet>(wallet,HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Wallet not found!");
        }
    }

    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> getWallets() {
        List<Wallet> wallets = walletService.getWallets();
        return new ResponseEntity<List<Wallet>>(wallets,HttpStatus.OK);
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> createWallet() {
        Wallet wallet = walletService.createWallet();
        if (wallet != null) {
            return new ResponseEntity<Wallet>(wallet, HttpStatus.CREATED);
        }
        else {
            throw new BusinessException("Something went wrong in the creation of the wallet :(");
        }
    }

    @GetMapping("/wallet/{walletId}/balances")
    public ResponseEntity<List<CurrencyWallet>> getCurrencyWallets(@PathVariable Long walletId) {
        Wallet wallet = walletService.getWallet(walletId);
        if (wallet != null) {
            List<CurrencyWallet> balances = walletService.getBalances(walletId);
            return new ResponseEntity<List<CurrencyWallet>>(balances,HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Wallet not found!");
        }
    }
}