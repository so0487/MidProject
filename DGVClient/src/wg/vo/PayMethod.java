package wg.vo;

import java.io.Serializable;

public class PayMethod implements Serializable {
	private int paymet_no;
	private String paymet_name;
	
	
	public int getPaymet_no() {
		return paymet_no;
	}
	public void setPaymet_no(int paymet_no) {
		this.paymet_no = paymet_no;
	}
	public String getPaymet_name() {
		return paymet_name;
	}
	public void setPaymet_name(String paymet_name) {
		this.paymet_name = paymet_name;
	}
	
	
}
