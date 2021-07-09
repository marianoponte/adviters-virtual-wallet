package com.adviters.virtualwallet.service;

import com.adviters.virtualwallet.model.Currency;
import com.adviters.virtualwallet.model.Quotation;
import com.adviters.virtualwallet.model.QuoteBtcResponse;
import com.adviters.virtualwallet.repository.CurrencyRepository;
import com.adviters.virtualwallet.repository.QuotationRepository;
import com.adviters.virtualwallet.util.Compare;
import com.adviters.virtualwallet.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
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
        List<Quotation> quotations = (List<Quotation>) quotationRepository.findAll();
        return quotations;
    }

    //Obetener cotizacion por una fecha determinada
    public Quotation getQuotationByDate(LocalDateTime date) {
        date = date.truncatedTo(ChronoUnit.SECONDS);
        Optional<Quotation> quotation = quotationRepository.findByQuotationDateParam(date);
        if (quotation.isPresent()) {
            return quotation.get();
        } else
            return null;
    }
    //Obtener todas las cotizaciones filtradas por fecha: desde y hasta
    public Quotation getQuotationsByFromAndToDates(LocalDateTime from, LocalDateTime to) {
        List<Quotation> quotations = (List<Quotation>) quotationRepository.findAllByFromAndToDates(from,to);

        if (quotations != null && !quotations.isEmpty()) {
            Quotation quotationWithMaxPrice = quotations.stream()
                    .reduce(Compare::max)
                    .get();

            return quotationWithMaxPrice;
        }
        return null;
    }

    //Con la notacion scheduled puedo hacer que cada 10 segundos se ejecute la llamada al servicio
    //de cotización de BTC e ir guardando la respuesta en la base de datos
    @Scheduled(fixedRate = 10000)
    public void loadQuoationsCron() throws JsonProcessingException {
        QuoteBtcResponse quoteBtcResponse = getBtcQuotationFromApi();
        System.out.println("***********************************");
        System.out.println("Response API BTC QUOTE:" + quoteBtcResponse);
        System.out.println("***********************************");

        Currency currency = currencyRepository.findByCurrencyName(quoteBtcResponse.getCurr1());
        System.out.println("***********************************");
        System.out.println("BTC CURRENCY:" + quoteBtcResponse.getCurr1());
        System.out.println("***********************************");

        System.out.println("***********************************");
        System.out.println("CURRENCY:" + currency.getCurrencyName());
        System.out.println("***********************************");

        Quotation quotation = new Quotation(currency, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), Double.parseDouble(quoteBtcResponse.getLprice()));
        quotationRepository.save(quotation);
        System.out.println("***********************************");
        System.out.println("QUOTATION:" + quotation);
        System.out.println("***********************************");

    }

    public QuoteBtcResponse getBtcQuotationFromApi() throws JsonProcessingException {
        //Declaro la clase webclient para hacer el llamado al servicio
        WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.USER_AGENT, "WebClient")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json").build();

        //Obtengo la respuesta de la api y la mapeo a una clase String
        // ya que no es un Json
         String response = webClient.get()
                .uri(Constants.URL_BTC_QUOTE)
                .retrieve()
                .bodyToMono(String.class)
                .block();

         //Casteo la respuesta obtenida en la clase String a una clase QuoteBtcResponse
         //para poder manipular los datos de mejor manera
         //para el mapeo utilizo Jackson o bien podria usar la libreria Gson
        QuoteBtcResponse quoteBtcResponse = new ObjectMapper().readValue(response, QuoteBtcResponse.class);
        return quoteBtcResponse;
    }
}