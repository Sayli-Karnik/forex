
		
package com.example.forex;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class TransactionOrderRepository {
	// private final static Logger logger = LoggerFactory.getLogger(TransactionOrderRepository.class);

	    private final JdbcTemplate jdbcTemplate;

	    public TransactionOrderRepository(JdbcTemplate jdbcTemplate) {
	      this.jdbcTemplate = jdbcTemplate;
	    }

		@Transactional(readOnly=true)
		public List<TransactionOrder> findAllOrders() {
			return jdbcTemplate.query("SELECT * FROM ORDERS", new OrderRowMapper());
		}
	    
		@Transactional
		public User addUser(User username) {
			
			System.out.println("Password must satisfy following criteria:");
			System.out.println("Password must contain atleast one LowerCase Character");
			System.out.println("Password must contain atleast one UpperCase Character");
			System.out.println("Password must contain atleast one Special Character [@,#,$,%]");
			System.out.println("Password must contain atleast one Numeral");
			System.out.println("Password must have length between 6 to 20 charactes");
			
			final String sql = "INSERT INTO USERS(username,password) values(?,?)";
			PasswordValidator pwd = new PasswordValidator();
			if(pwd.validate(username.getPassword()) == true){
			jdbcTemplate.update(sql, username.getUsername(), pwd.md5(username.getPassword()));
			System.out.println("User Registered Succesfully");
			}
			else 
			{
				System.out.println("Please Enter a Valid Password");
			}
			return username;
		}
	    
		
		@Transactional
	    public TransactionOrder saveOrder(TransactionOrder order) {
	    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	
	    	  String type=order.getSide();
	    	  String query;
	    	  String order_type=order.getOrder_type();
	    	  System.out.println("Type is " + type);
	          String other_type;
	          String buyer = null;
	          String seller=null;
	            if(type.equals("BUY")){
	            	System.out.println("In type BUY");
	                buyer=order.getUsername();
	                other_type="SELL";
	            }
	            else{
	            	System.out.println("In type SELL");
	                seller=order.getUsername();
	                other_type="BUY";
	            }

	            double price=order.getPrice();
	            System.out.println("Seller is " + seller);
	            String pair=order.getPair();
	            int quantity=order.getQuantity();
	            
	            List<OpenClosedOrders> list3 = new ArrayList<OpenClosedOrders>();
	            String sql3="select PRICE from SUC_TRANSACTION where PAIR= '" +order.getPair()+ "' order by TID DESC";
	            list3=jdbcTemplate.query(sql3,new UserRow());
	            double market_price=list3.get(0).getPrice();
	            
	            if(order_type.equals("MARKET"))
	            	query="select * from ORDERS where SIDE='" + other_type + "' and PAIR='" + pair + "' and QTY=" + quantity;
	            else
	            	{
		            	if(type.equals("BUY"))
		            		query="select * from ORDERS where SIDE='" + other_type + "' and PAIR='" + pair + "' and QTY=" + quantity + " and PRICE<=" + price;
		            	else
		            		query="select * from ORDERS where SIDE='" + other_type + "' and PAIR='" + pair + "' and QTY=" + quantity + " and PRICE>=" + price;
		            		
	            	}	
	            
	            System.out.println(query);
	            List<TransactionOrder> list1 = new ArrayList<TransactionOrder>();
	            list1=jdbcTemplate.query(query, new UserMapper());
	            int size=list1.size();
	            System.out.println(size);


	if(user_exists(order.getUsername(),order.getPassword()))
	{
		System.out.println("Authenticated");
	    if(size>0)
	    {
	        
	        	System.out.println("Size is 1");
	            int id=list1.get(0).getOid();
	            String name= list1.get(0).getUsername();
	            System.out.println(name);
	            if(buyer== null){
	            	
	                buyer=name;
	                System.out.println("Buyer is " + buyer);
	            }
	            else
	            {
		            seller=name;
	            }
	            if(order_type.equals("MARKET")){
		            
		            String query1= "insert into SUC_TRANSACTION(BUYER, SELLER,PAIR, PRICE, QTY,TIME_STAMP) values (?,?,?,?,?,?,)";
		            jdbcTemplate.update(query1, buyer, seller,order.getPair(), market_price, order.getQuantity(), timeStamp);
		            
		            jdbcTemplate.update("delete from ORDERS where OID=?",id);
	            }
	            else
	            {
	            	String query1= "insert into SUC_TRANSACTION(BUYER, SELLER,PAIR, PRICE, QTY,TIME_STAMP) values (?,?,?,?,?,?,)";
		            jdbcTemplate.update(query1, buyer, seller,order.getPair(), order.getPrice(), order.getQuantity(), timeStamp);
		            
		            jdbcTemplate.update("delete from ORDERS where OID=?",id);
	            }

	           
	        }
	        
	    else
	    {
	    	if(order_type.equals("MARKET"))
		        jdbcTemplate.update("insert into ORDERS(USERNAME, ORDER_TYPE,PRICE, QTY, PAIR, SIDE, TIME_STAMP,) values (?,?,?,?,?,?,?)",
		        order.getUsername(), order.getOrder_type(), market_price,order.getQuantity(),order.getPair(), order.getSide(), timeStamp);
	    	else
	    		jdbcTemplate.update("insert into ORDERS(USERNAME, ORDER_TYPE,PRICE, QTY, PAIR, SIDE, TIME_STAMP,) values (?,?,?,?,?,?,?)",
	    		order.getUsername(), order.getOrder_type(), order.getPrice(),order.getQuantity(),order.getPair(), order.getSide(), timeStamp);
	    }
	}
	       return order;

	        }

	    	
//if(user_exists(order.getUsername(),order.getPassword()))
//	    	jdbcTemplate.update("insert into ORDERS(USERNAME, ORDER_TYPE, PRICE, QTY, PAIR, SIDE, TIME_STAMP,) values (?,?,?,?,?,?,?)", 
//	    order.getUsername(), order.getOrder_type(), order.getPrice(), order.getQuantity(),order.getPair(), order.getSide(), timeStamp);
//	        
//	   return order;
//	    }

//	    @Transactional(readOnly=true)
//	    public List<TransactionOrder> findAllOrders() {
//	        return jdbcTemplate.query("select TransactionOrder, AMOUNT, SIDE from MARKET_ORDERS",
//	                (new RowMapper<TransactionOrder>() {
//
//	                    @Override
//	                    public TransactionOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
//	                      TransactionOrder order = new TransactionOrder();
//	                      order.setTransactionOrder(TransactionOrder.valueOf(rs.getString("TransactionOrder")));
//	                      order.setAmount(rs.getDouble("AMOUNT"));
//	                      order.setSide(Side.valueOf(rs.getString("SIDE")));
//	                      return order;
//	                    }
//
//	                }));
	    public boolean user_exists(String username, String password)

	    {String sql= "select * from USERS where username=? and password=?";
	    	if(jdbcTemplate.queryForObject(sql, new Object[]{username, password},new UserRowMapper()) != null)
	    			return true;
	    	else return false;
	    }
	    
	    }

class UserRowMapper implements RowMapper<User>

{
	 @Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
       User user = new User();
       user.setUsername(rs.getString("USERNAME"));
       user.setPassword(rs.getString("PASSWORD"));
      return user;
     }	
}

class OrderRowMapper implements RowMapper<TransactionOrder>
{
	@Override
	public TransactionOrder mapRow(ResultSet rs, int rowNum) throws SQLException{
		TransactionOrder order = new TransactionOrder();

		order.setUsername(rs.getString("USERNAME"));
		//order.setPassword(rs.getString("PASSWORD"));
		order.setOid(rs.getInt("OID"));
		order.setSide(rs.getString("SIDE"));
		order.setOrder_type(rs.getString("ORDER_TYPE"));
		order.setPair(rs.getString("PAIR"));
		order.setPrice(rs.getDouble("PRICE"));
		order.setQuantity(rs.getInt("QTY"));
		//order.set(rs.getString("TIME_STAMP"));
		return order;
	}
}
class UserMapper implements RowMapper<TransactionOrder>

{
     @Override
    public TransactionOrder mapRow(ResultSet rs, int rowNum) throws
SQLException {
       TransactionOrder order = new TransactionOrder();

       order.setOid(rs.getInt("OID"));
       order.setUsername(rs.getString("USERNAME"));

      return order;
     }
}
//{
//    "username": "SAYLI",
//    "order_type": "market",
//    "price": 2,
//    "quantity": 50,
//    "pair": "EUR/USD",
//    "side": "buy",
//   "password":"Sbc@123"
//}


class UserRow implements RowMapper<OpenClosedOrders>

{
     @Override
    public OpenClosedOrders mapRow(ResultSet rs, int rowNum) throws SQLException {
       OpenClosedOrders oco = new OpenClosedOrders();
       oco.setPrice(rs.getDouble("PRICE"));
       return oco;
     }
}

