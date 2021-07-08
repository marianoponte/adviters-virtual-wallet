package com.adviters.virtualwallet.service;

import com.adviters.virtualwallet.model.CurrencyWallet;
import com.adviters.virtualwallet.model.Wallet;
import com.adviters.virtualwallet.repository.CurrencyRepository;
import com.adviters.virtualwallet.repository.WalletRepository;
import com.adviters.virtualwallet.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Servicio de billetera

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CurrencyWalletService currencyWalletService;

    @Autowired
    private CurrencyRepository currencyRepository;

    //Obtener una billetersa por id
    public Wallet getWallet(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent()) {
            return wallet.get();
        } else
            return null;
    }

    //Obtener todas las billeteras
    public List<Wallet> getWallets() {
        return (List<Wallet>) walletRepository.findAll();
    }

    //Creacion de billetera -> ya se genera los registros en la tabla intermedia para cada
    //moneda cargada y asi poder consultar el saldo inicial
    public Wallet createWallet() {
        LocalDateTime timestamp = LocalDateTime.now();
        Wallet wallet = walletRepository.save(new Wallet(timestamp));

        currencyRepository.findAll().forEach(currency -> currencyWalletService.createCurrencyWallet(new CurrencyWallet(
                currency,
                wallet,
                Constants.INIT_AMOUNT,
                timestamp)
        ));

        return wallet;
    }

    public List<CurrencyWallet> getBalances(Long walletId) {
        return currencyWalletService.getBalancesByWalletId(walletId);
    }
}