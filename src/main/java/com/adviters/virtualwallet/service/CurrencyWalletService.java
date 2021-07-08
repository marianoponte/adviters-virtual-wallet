package com.adviters.virtualwallet.service;

import com.adviters.virtualwallet.model.CurrencyWallet;
import com.adviters.virtualwallet.repository.CurrencyWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyWalletService {

    @Autowired
    private CurrencyWalletRepository currencyWalletRepository;

    //Obtener un registro de currency wallet por id
    public CurrencyWallet  getCurrencyWallet(Long id) {
        Optional<CurrencyWallet> currencyWallet = currencyWalletRepository.findById(id);
        if (currencyWallet.isPresent()) {
            return currencyWallet.get();
        } else
            return null;
    }

    public List<CurrencyWallet> getBalancesByWalletId(Long id) {
        return currencyWalletRepository.findAllByWallet_Id(id);
    }

    public CurrencyWallet createCurrencyWallet(CurrencyWallet currencyWallet) {
        return currencyWalletRepository.save(currencyWallet);
    }

    public CurrencyWallet updateCurrencyWallet(CurrencyWallet currencyWallet) {
        return currencyWalletRepository.save(currencyWallet);
    }
}