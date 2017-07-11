package com.example.forex;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class TransactionOrderRepository {
	// private final static Logger logger = LoggerFactory.getLogger(TransactionOrderRepository.class);

	    private final JdbcTemplate jdbcTemplate;

	    public TransactionOrderRepository(JdbcTemplate jdbcTemplate) {
	      this.jdbcTemplate = jdbcTemplate;
	    }

	    @Transactional
	    public TransactionOrder saveOrder(TransactionOrder order) {
	    jdbcTemplate.update("insert into SUC_TRANSACTION(USERNAME, CID, QUANTITY, AMOUNT, STATUS) values (?,?,?,?,'m')", 
	    order.getUsername(), order.getCid(), order.getQuantity(), order.getAmount());
	        
	   return order;
	    }

//	    @Transactional(readOnly=true)
//	    public List<TransactionOrder> findAllOrders() {
//	        return jdbcTemplate.query("select CURRENCY, AMOUNT, SIDE from MARKET_ORDERS",
//	                (new RowMapper<TransactionOrder>() {
//
//	                    @Override
//	                    public TransactionOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
//	                      TransactionOrder order = new TransactionOrder();
//	                      order.setCurrency(Currency.valueOf(rs.getString("CURRENCY")));
//	                      order.setAmount(rs.getDouble("AMOUNT"));
//	                      order.setSide(Side.valueOf(rs.getString("SIDE")));
//	                      return order;
//	                    }
//
//	                }));
	    }

		


