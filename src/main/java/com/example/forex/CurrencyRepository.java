package com.example.forex;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class CurrencyRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
	public List<Currency> findAllCurrency() {
		return jdbcTemplate.query("SELECT * FROM CURRENCY", new CurrencyRowMapper());
	}

}

class CurrencyRowMapper implements RowMapper<Currency>
{
	@Override
	public Currency mapRow(ResultSet rs, int rowNum) throws SQLException{
		Currency currency = new Currency();
		currency.setPair(rs.getString("PAIR"));
		currency.setRate(rs.getDouble("RATE"));
		return currency;
	}
}