package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.exception.CurrencyException;
import com.alexbro.onlinebank.facade.currency.CurrencyFacade;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.Mapping.EXCHANGE_STEP_ONE)
public class ExchangeStepOnePageController {

    @Resource
    private CurrencyFacade currencyFacade;

    @Resource
    private UserFacade userFacade;

    private Logger logger = LoggerFactory.getLogger(ExchangeStepTwoPageController.class);

    @GetMapping
    public String getExchangeStepOnePage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        Optional<AuthData> authData = Optional.ofNullable((AuthData) request.getSession().getAttribute(WebConstants.SessionAttributes.AUTH_DATA));
        try {
            authData.flatMap(ad -> userFacade.findByCode(ad.getUserCode())).ifPresent(u -> model.
                    addAttribute(WebConstants.RequestAttributes.CURRENCIES, currencyFacade.findAllByUser(u.getCode())));
            return WebConstants.Pages.EXCHANGE_STEP_ONE;
        } catch (CurrencyException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            logger.info(e.getMessage(), e);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.USER + "/" + authData.get().getUserCode();
        }
    }
}
