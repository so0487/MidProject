package wg.vo;

import java.io.Serializable;

public class PayDetailVO implements Serializable {
	private int paydet_no;
	private int pay_no;
	private int paymet_no;
	
	public int getPaydet_no() {
		return paydet_no;
	}
	public void setPaydet_no(int paydet_no) {
		this.paydet_no = paydet_no;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getPaymet_no() {
		return paymet_no;
	}
	public void setPaymet_no(int paymet_no) {
		this.paymet_no = paymet_no;
	}
	
	
	
	
}
