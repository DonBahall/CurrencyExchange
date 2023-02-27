package com.example.currencyexchange.Controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    final Map<String, String> exchangeRates ;

    public MainController(Map<String, String> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPrices(Model model)  {
        model.addAttribute("money", exchangeRates.keySet());
        return "Exchange";
    }
}
