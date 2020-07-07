package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(WebConstants.Mapping.EXCHANGE_STEP_TWO)
public class ExchangeStepTwoPageController {

    @GetMapping
    public String getExchangeStepTwoPage(@RequestParam String currencyFromCode,
                                         @RequestParam String currencyToCode) {
        return WebConstants.Pages.EXCHANGE_STEP_TWO;
    }
}
