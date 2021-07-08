package com.adviters.virtualwallet.controller;

import com.adviters.virtualwallet.exception.ResourceNotFoundException;
import com.adviters.virtualwallet.model.Quotation;
import com.adviters.virtualwallet.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/quotations")
    public ResponseEntity<List<Quotation>> getQuotations() {
        List<Quotation> quotations = quotationService.getQuotations();
        return new ResponseEntity<List<Quotation>>(quotations,HttpStatus.OK);
    }
}
