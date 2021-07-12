package com.adviters.virtualwallet.controller;

import com.adviters.virtualwallet.exception.BusinessException;
import com.adviters.virtualwallet.exception.ResourceNotFoundException;
import com.adviters.virtualwallet.model.Quotation;
import com.adviters.virtualwallet.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @GetMapping("/quotation/{id}")
    public ResponseEntity<Quotation> getQuotation(@PathVariable Long id) {
        Quotation quotation = quotationService.getQuotation(id);
        if (quotation != null) {
            return new ResponseEntity<Quotation>(quotation, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Quotation not found!");
        }
    }

    @GetMapping("/quotations/max")
    public ResponseEntity<Quotation> getQuotationsMax(@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from, @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        Quotation quotation;
        if (from != null && to != null) {
            quotation = quotationService.getQuotationsByFromAndToDates(from, to);
        }
        else {
            throw new BusinessException("Dates from and to are needed to know the highest quote!");
        }
        return new ResponseEntity<Quotation>(quotation,HttpStatus.OK);
    }

    @GetMapping("/quotations")
    public ResponseEntity<List<Quotation>> getQuotations(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<Quotation> quotations = new ArrayList<>();
        if (date != null) {
            quotations.add(quotationService.getQuotationByDate(date));
        }
        else {
            quotations = quotationService.getQuotations();
        }
        return new ResponseEntity<List<Quotation>>(quotations,HttpStatus.OK);
    }
}
