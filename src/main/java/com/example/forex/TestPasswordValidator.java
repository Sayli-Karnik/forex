package com.example.forex;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PasswordValidator.class})

public class TestPasswordValidator {

	@Autowired
	private PasswordValidator passwordValidator;
	
	
	@Test
	public void testValidator()
	{
		String encryptedPassword = "2a2a3bb27cffa1d9769f3d8e4dfd3ca2";
		assertEquals(encryptedPassword,passwordValidator.md5("Steverogers@19634"));
		assertEquals(true,passwordValidator.validate("Steverogers@19634"));	
	}

}
