package wg.vo;

import java.io.Serializable;

public class PayVO implements Serializable{
	private int pay_no;
	private String bid_id;
	private String book_id;
	private String buy_id;
	private int pay_price;
	private String pay_time;
	private String pay_refund;
	private int paymet_no;
	
	
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public String getBid_id() {
		return bid_id;
	}
	public void setBid_id(String bid_id) {
		this.bid_id = bid_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(String buy_id) {
		this.buy_id = buy_id;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getPay_refund() {
		return pay_refund;
	}
	public void setPay_refund(String pay_refund) {
		this.pay_refund = pay_refund;
	}
	public int getPaymet_no() {
		return paymet_no;
	}
	public void setPaymet_no(int paymet_no) {
		this.paymet_no = paymet_no;
	}
	
	
	
	
	
}
