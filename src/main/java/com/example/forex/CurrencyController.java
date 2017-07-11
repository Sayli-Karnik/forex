package com.example.forex;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyController {
	
	@Autowired
	private CurrencyRepository caserepo;
	
	@RequestMapping("/currencyrate")
	public List<Currency> findCurrencyRates()
	{
		return caserepo.findAllCurrency();
	}
	
	/*@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public void UserRegistration(@RequestBody UserName username) {
		
	}*/
}
