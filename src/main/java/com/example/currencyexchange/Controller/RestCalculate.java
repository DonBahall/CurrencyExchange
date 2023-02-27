package com.example.currencyexchange.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RestCalculate {

    final Map<String, String> exchangeRates;

    public RestCalculate(Map<String, String> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public String count(@RequestParam String from, @RequestParam String to, @RequestParam String amount) {
        if(amount.isEmpty()) return null;
        try {
            double fromRate = Double.parseDouble(exchangeRates.get(from));
            double toRate = Double.parseDouble(exchangeRates.get(to));
            return String.valueOf(Double.parseDouble(amount) * fromRate / toRate);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return null;
    }
}
