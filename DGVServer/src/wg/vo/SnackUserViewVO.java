package wg.vo;

import java.io.Serializable;
import java.util.List;

public class SnackUserViewVO implements Serializable {
	
	private String set_id;			
	private String set_name;
	private String set_photo; 
	private int pay_price;	
	private String pay_refund;		
	private String buy_id;		
	private String mem_id;	
	private int buydet_no;
	private int buydet_qty;
	
	
	public String getSet_id() {
		return set_id;
	}
	public void setSet_id(String set_id) {
		this.set_id = set_id;
	}
	public String getSet_name() {
		return set_name;
	}
	public void setSet_name(String set_name) {
		this.set_name = set_name;
	}
	public String getSet_photo() {
		return set_photo;
	}
	public void setSet_photo(String set_photo) {
		this.set_photo = set_photo;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public String getPay_refund() {
		return pay_refund;
	}
	public void setPay_refund(String pay_refund) {
		this.pay_refund = pay_refund;
	}
	public String getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(String buy_id) {
		this.buy_id = buy_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getBuydet_no() {
		return buydet_no;
	}
	public void setBuydet_no(int buydet_no) {
		this.buydet_no = buydet_no;
	}
	public int getBuydet_qty() {
		return buydet_qty;
	}
	public void setBuydet_qty(int buydet_qty) {
		this.buydet_qty = buydet_qty;
	}
	
	
	
	
	
	
	

	
}
