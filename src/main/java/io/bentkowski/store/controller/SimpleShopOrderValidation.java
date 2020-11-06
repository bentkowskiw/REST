package io.bentkowski.store.controller;


import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SimpleShopOrderValidation implements  ShopOrderValidation {

    private static final String regex = "^(.+)@(.+)$";

    @Override
    public void validate(ShopOrderDto shopOrderDto) throws ShopOrderValidationError {


        String email = shopOrderDto.getBuyersEmail();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches())
            throw new ShopOrderValidationError(shopOrderDto, "buyersEmail",email);


    }
}
