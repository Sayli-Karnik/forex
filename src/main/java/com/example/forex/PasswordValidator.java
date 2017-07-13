package com.example.forex;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator{

	 private Pattern pattern;
	 private Matcher matcher;

	 private static final String PASSWORD_PATTERN =
              "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	 public PasswordValidator(){
		 pattern = Pattern.compile(PASSWORD_PATTERN);
	 }

	 public boolean validate(final String password){

		 matcher = pattern.matcher(password);
		 return matcher.matches();

	 }
	 public String md5(String input) {
			
			String md5 = null;
			
			if(null == input) return null;
			
			try {
				
			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			//Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			//Converts message digest value in base 16 (hex) 
			md5 = new BigInteger(1, digest.digest()).toString(16);

			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			}
			return md5;
		}
	 
}


