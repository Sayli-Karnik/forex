package com.example.forex;

public class Orders {
private String username;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getOrder_type() {
	return order_type;
}
public void setOrder_type(String Order_type) {
	this.order_type = Order_type;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getQty() {
	return qty;
}
public void setQty(double qty) {
	this.qty = qty;
}
public String getPair() {
	return pair;
}
public void setPair(String pair) {
	this.pair = pair;
}
public String getSide() {
	return side;
}
public void setSide(String side) {
	this.side = side;
}
private String order_type;
private double price;
private double qty;
private String pair;
private String side;
}
