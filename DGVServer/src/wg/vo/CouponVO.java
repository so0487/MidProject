package wg.vo;

import java.io.Serializable;

public class CouponVO implements Serializable {
	private String cou_id;
	private String cou_name;
	private int cou_sale;
	private String cou_time;
	
	
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public int getCou_sale() {
		return cou_sale;
	}
	public void setCou_sale(int cou_sale) {
		this.cou_sale = cou_sale;
	}
	public String getCou_time() {
		return cou_time;
	}
	public void setCou_time(String cou_time) {
		this.cou_time = cou_time;
	}
	
	
}
