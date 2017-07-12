package com.example.forex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CancelOrderController {
	@Autowired
	CancelOrderRepository caorder;
	
//	@RequestMapping(value="/TransactionOrders", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
//	public List<TransactionOrder> getTransactionOrders(){
//		return usr.findAllOrders();
//	}
//	

    
    @RequestMapping(value="/cancelorder", method=RequestMethod.POST)
	public Orders cancelUserOrder(@RequestBody CancelOrder u)
	{
    	System.out.println(u.toString());
    	 return caorder.cancelOrder(u);
	}

}
