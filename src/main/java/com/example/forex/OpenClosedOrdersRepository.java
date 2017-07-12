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
public class OpenClosedOrdersRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Transactional(readOnly = true)
	public List<Orders> findOpenOrders(String username) {
		return jdbctemplate.query("SELECT * FROM ORDERS WHERE USERNAME = ?",new Object[]{username}, new OpenRowMapper());
	}
	
	@Transactional(readOnly = true)
	public List<OpenClosedOrders> findClosedOrders(String username) {
		return jdbctemplate.query("SELECT * FROM SUC_TRANSACTION WHERE BUYER = '"+ username + "'OR SELLER ='" + username + "'", new ClosedRowMapper());
	}


}

class OpenRowMapper implements RowMapper<Orders>
{
	@Override
	public Orders mapRow(ResultSet rs, int rowNum) throws SQLException{
		Orders orders = new Orders();
		orders.setUsername(rs.getString("USERNAME"));
		orders.setSide(rs.getString("SIDE"));
		orders.setOrder_type(rs.getString("ORDER_TYPE"));
		orders.setPair(rs.getString("PAIR"));
		orders.setPrice(rs.getDouble("PRICE"));
		orders.setQty(rs.getDouble("QTY"));
	//	orders.setTime_Stamp(rs.getString("TIME_STAMP"));
		return orders;
	}
}

class ClosedRowMapper implements RowMapper<OpenClosedOrders>
{
	@Override
	public OpenClosedOrders mapRow(ResultSet rs, int rowNum) throws SQLException{
		OpenClosedOrders openclosedorders = new OpenClosedOrders();
		openclosedorders.setBuyer(rs.getString("BUYER"));
		openclosedorders.setSeller(rs.getString("SELLER"));
		openclosedorders.setPair(rs.getString("PAIR"));
		openclosedorders.setPrice(rs.getDouble("PRICE"));
		openclosedorders.setQty(rs.getDouble("QTY"));
		openclosedorders.setTime_stamp(rs.getString("TIME_STAMP"));
		return openclosedorders;
	}
}
