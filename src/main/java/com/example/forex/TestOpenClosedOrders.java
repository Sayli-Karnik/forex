package com.example.forex;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OpenClosedOrders.class})
public class TestOpenClosedOrders {

	@Autowired
	private OpenClosedOrders currency;
	
	@Test
	public void testOpenClosed()
	{
		currency.setBuyer("RATAN");
		currency.setPair("USD/INR");
		currency.setPrice(1.5);
		currency.setQty(1000);
		currency.setSeller("ROHIT");
		assertEquals("RATAN",currency.getBuyer());
		assertEquals("USD/INR",currency.getPair());
		assertEquals(1.5,currency.getPrice(),0.0f);
		assertEquals(1000,currency.getQty(),0.0f);
		assertEquals("ROHIT",currency.getSeller());
	}
	
}