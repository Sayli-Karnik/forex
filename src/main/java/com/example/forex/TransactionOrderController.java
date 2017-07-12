package com.example.forex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionOrderController {

	
	@Autowired
	TransactionOrderRepository trans;
	
//	@RequestMapping(value="/TransactionOrders", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
//	public List<TransactionOrder> getTransactionOrders(){
//		return usr.findAllOrders();
//	}
//	

    
    @RequestMapping(name="/placeorder", method=RequestMethod.POST)
	public TransactionOrder createTransactionOrder(@RequestBody TransactionOrder u)
	{
    	 return trans.saveOrder(u);
	}
    @RequestMapping("/orders")
	public List<TransactionOrder> findOrders()
	{
		return trans.findAllOrders();
	}
    

    

}
