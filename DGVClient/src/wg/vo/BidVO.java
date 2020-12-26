package wg.vo;

import java.io.Serializable;

public class BidVO implements Serializable {
	private String bid_id;
	private String mem_id;
	private int auc_no;
	private String bid_time;
	private int bid_price;
	
	public String getBid_id() {
		return bid_id;
	}
	public void setBid_id(String bid_id) {
		this.bid_id = bid_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getAuc_no() {
		return auc_no;
	}
	public void setAuc_no(int auc_no) {
		this.auc_no = auc_no;
	}
	public String getBid_time() {
		return bid_time;
	}
	public void setBid_time(String bid_time) {
		this.bid_time = bid_time;
	}
	public int getBid_price() {
		return bid_price;
	}
	public void setBid_price(int bid_price) {
		this.bid_price = bid_price;
	}
	
	
	
	
}
