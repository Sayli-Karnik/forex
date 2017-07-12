package com.example.forex;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenClosedOrdersController {

	@Autowired
	private OpenClosedOrdersRepository openrepo;
	
	@RequestMapping(value = "/openorders",method = RequestMethod.POST)
	public List<Orders> findTransactions(@RequestBody User o)
	{
		return openrepo.findOpenOrders(o.getUsername());
	}
	
	@RequestMapping(value = "/closedorders",method = RequestMethod.POST)
	public List<OpenClosedOrders> findClosedTransactions(@RequestBody User o)
	{
		return openrepo.findClosedOrders(o.getUsername());
	}
	
}