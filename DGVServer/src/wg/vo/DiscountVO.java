package wg.vo;

import java.io.Serializable;

public class DiscountVO implements Serializable{

	private String dis_id;
	private String dis_name;
	private int dis_price;
	private String dis_if;
	
	public String getDis_id() {
		return dis_id;
	}
	public void setDis_id(String dis_id) {
		this.dis_id = dis_id;
	}
	public String getDis_name() {
		return dis_name;
	}
	public void setDis_name(String dis_name) {
		this.dis_name = dis_name;
	}
	public int getDis_price() {
		return dis_price;
	}
	public void setDis_price(int dis_price) {
		this.dis_price = dis_price;
	}
	public String getDis_if() {
		return dis_if;
	}
	public void setDis_if(String dis_if) {
		this.dis_if = dis_if;
	}
	
	
}
