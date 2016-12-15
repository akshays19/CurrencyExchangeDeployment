package com.currency.exchange;

import org.json.JSONObject;
import org.junit.Test;

import service.CurrencyExchangeService;

public class CurrencyExchangeServiceTest {

	CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
	
	private static final String supportedCurrenciesArr[] = { "USD", "AUD",
			"CAD", "GBP", "EUR" };
	
	
	@Test
	public void getExchangeRate(){
		JSONObject rates = currencyExchangeService.getExchangeRateFromAPI(supportedCurrenciesArr);
		System.out.println(rates);
	}
}
