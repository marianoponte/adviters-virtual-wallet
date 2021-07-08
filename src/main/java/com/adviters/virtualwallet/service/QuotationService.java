package com.adviters.virtualwallet.service;

import com.adviters.virtualwallet.model.Currency;
import com.adviters.virtualwallet.model.Quotation;
import com.adviters.virtualwallet.model.QuoteBtcResponse;
import com.adviters.virtualwallet.repository.CurrencyRepository;
import com.adviters.virtualwallet.repository.QuotationRepository;
import com.adviters.virtualwallet.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Clase de servicio para las cotizaciones

@EnableScheduling
@Service
public class QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    //Obetener una cotizacion por id
    public Quotation getQuotation(Long id) {
        Optional<Quotation> quotation = quotationRepository.findById(id);
        if (quotation.isPresent()) {
            return quotation.get();
        } else
            return null;
    }

    //Obetener todas las cotizaciones
    public List<Quotation> getQuotations() {
        loadQuoationsCron();
        List<Quotation> quotations = (List<Quotation>) quotationRepository.findAll();
        return quotations;
    }

    //@Scheduled(fixedRate = 10000)
    public void loadQuoationsCron() {
        QuoteBtcResponse response = getBtcQuotationFromApi();
        System.out.println("***********************************");
        System.out.println("Response API BTC QUOTE:" + response);
        System.out.println("***********************************");

        Currency currency = currencyRepository.findByCurrencyName(response.getCurr1());
        Quotation quotation = new Quotation(currency, LocalDateTime.now(), new BigDecimal(response.getLprice()));
        quotationRepository.save(quotation);
    }

    public QuoteBtcResponse getBtcQuotationFromApi() {
        WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.USER_AGENT, "WebClient")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json").build();
        return webClient.get()
                .uri(Constants.URL_BTC_QUOTE)
                .retrieve()
                .bodyToMono(QuoteBtcResponse.class)
                .block();
    }
}
