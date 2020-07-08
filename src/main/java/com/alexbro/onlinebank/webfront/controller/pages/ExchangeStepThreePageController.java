package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
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
    private AccountFacade accountFacade;

    @GetMapping
    public String getExchangeStepThreePage(@RequestParam String accountCode, @RequestParam Long cardNumber,
                                           @RequestParam BigDecimal sum, Model model) {
        AccountData accountFrom = accountFacade.findByCode(accountCode).orElseThrow();
        AccountData accountTo = accountFacade.findByCardNumber(cardNumber).orElseThrow();
        BigDecimal sumAfter = accountFacade.calculateSumAfterExchange(sum, accountFrom.getCurrency().getRate(),
                accountTo.getCurrency().getRate());
        model.addAttribute(WebConstants.RequestAttributes.ACCOUNT_FROM, accountFrom);
        model.addAttribute(WebConstants.RequestAttributes.ACCOUNT_TO, accountTo);
        model.addAttribute(WebConstants.RequestAttributes.SUM, sum);
        model.addAttribute(WebConstants.RequestAttributes.SUM_AFTER, sumAfter);
        model.addAttribute(WebConstants.RequestAttributes.BALANCE_FROM, accountFrom.getMoney());
        model.addAttribute(WebConstants.RequestAttributes.BALANCE_TO, accountTo.getMoney());
        model.addAttribute(WebConstants.RequestAttributes.BALANCE_AFTER_FROM, accountFacade.calculateBalanceWithDelta(accountFrom.getMoney(), sum.negate()));
        model.addAttribute(WebConstants.RequestAttributes.BALANCE_AFTER_TO, accountFacade.calculateBalanceWithDelta(accountTo.getMoney(), sumAfter));
        return WebConstants.Pages.EXCHANGE_STEP_THREE;
    }
}
