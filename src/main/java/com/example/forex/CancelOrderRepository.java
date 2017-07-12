package com.example.forex;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CancelOrderRepository {
	
	private final JdbcTemplate jdbcTemplate;

    public CancelOrderRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Orders cancelOrder(CancelOrder corder) {
    	Orders temp=new Orders();
    	int key= corder.oid;
    	if(user_exists(corder.getUsername(),corder.getPassword())){
    temp = jdbcTemplate.queryForObject("select * from ORDERS where OID=?", 
				new Object[]{key}, new CancelRowMapper());
    	//System.out.println("blah blah blah blah"+temp.getType());
    jdbcTemplate.update("insert into CAN_ORDERS(USERNAME, ORDER_TYPE, PRICE, QTY, PAIR, SIDE) values (?,?,?,?,?,?)", 
    temp.getUsername(), temp.getOrder_type(), temp.getPrice(), temp.getQty(), temp.getPair(), temp.getSide());
    
    jdbcTemplate.update("delete from ORDERS where OID=?", corder.oid);
    	}
   return temp;
    }
    
     boolean user_exists(String username, String password)

    {String sql= "select * from USERS where username=? and password=?";
    	if(jdbcTemplate.queryForObject(sql, new Object[]{username, password},new UserRowMapper()) != null)
    			return true;
    	else return false;
    }
    
    }



class CancelRowMapper implements RowMapper<Orders>
{
	@Override
	public Orders mapRow(ResultSet rs, int rowNum) throws SQLException{
		Orders mo= new Orders();
		mo.setUsername(rs.getString("USERNAME"));
		mo.setOrder_type(rs.getString("ORDER_TYPE"));
		mo.setPair(rs.getString("PAIR"));
		mo.setSide(rs.getString("SIDE"));
		mo.setPrice(rs.getDouble("PRICE"));
		mo.setQty(rs.getDouble("QTY"));
		return mo;
	}
}
