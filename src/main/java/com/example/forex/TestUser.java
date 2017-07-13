package com.example.forex;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {User.class})
public class TestUser {

	@Autowired
	User username;
	
	
	@Test
	public void testUserName()
	{
		username.setUsername("RATAN");
		username.setPassword("Steverogers19634");
		assertEquals("RATAN",username.getUsername());
		assertEquals("Steverogers19634",username.getPassword());
	}
	
}