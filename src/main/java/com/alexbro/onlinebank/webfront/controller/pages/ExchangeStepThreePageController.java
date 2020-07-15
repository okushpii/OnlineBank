package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.exchange.ExchangeFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Controller
@RequestMapping(WebConstants.Mapping.EXCHANGE_STEP_THREE)
public class ExchangeStepThreePageController {

    @Resource
    private ExchangeFacade exchangeFacade;

    @GetMapping
    public String getExchangeStepThreePage(@RequestParam String accountCode, @RequestParam Long cardNumber,
                                           @RequestParam BigDecimal sum, Model model) {
        model.addAttribute(WebConstants.RequestAttributes.EXCHANGE,
                exchangeFacade.getExchangeData(accountCode, cardNumber, sum));
        return WebConstants.Pages.EXCHANGE_STEP_THREE;
    }
}
