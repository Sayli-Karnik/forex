package com.example.forex;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ForexApplication.class})
public class TestNewUser {
	@Autowired
	private TransactionOrderRepository caseStudyRepository;
	
	
	@Test
	public void testNewUser()
	{
		User username = new User();
		username.setUsername("AJJU");
		username.setPassword("Sukhi@196");
		User obj = caseStudyRepository.addUser(username);
		assertEquals(obj.getUsername(),"AJJU");
		assertEquals(obj.getPassword(),"Sukhi@196");
	}

}
