package wg.vo;

import java.io.Serializable;

public class Buy_DetailVO implements Serializable {
	private int buydet_no;
	private String set_id;
	private String buy_id;
	private int buydet_qty;
	
	
	public int getBuydet_no() {
		return buydet_no;
	}
	public void setBuydet_no(int buydet_no) {
		this.buydet_no = buydet_no;
	}
	public String getSet_id() {
		return set_id;
	}
	public void setSet_id(String set_id) {
		this.set_id = set_id;
	}
	public String getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(String buy_id) {
		this.buy_id = buy_id;
	}
	public int getBuydet_qty() {
		return buydet_qty;
	}
	public void setBuydet_qty(int buydet_qty) {
		this.buydet_qty = buydet_qty;
	}
	
	
	
}
