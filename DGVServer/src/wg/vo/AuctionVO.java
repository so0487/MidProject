package wg.vo;

import java.io.Serializable;

public class AuctionVO implements Serializable{
	private int auc_no;
	private int sseat_no;
	private String auc_title;
	private String auc_startTime;
	private String auc_endTime;
	public int getAuc_no() {
		return auc_no;
	}
	public void setAuc_no(int auc_no) {
		this.auc_no = auc_no;
	}
	public int getSseat_no() {
		return sseat_no;
	}
	public void setSseat_no(int sseat_no) {
		this.sseat_no = sseat_no;
	}
	public String getAuc_title() {
		return auc_title;
	}
	public void setAuc_title(String auc_title) {
		this.auc_title = auc_title;
	}
	public String getAuc_startTime() {
		return auc_startTime;
	}
	public void setAuc_startTime(String auc_startTime) {
		this.auc_startTime = auc_startTime;
	}
	public String getAuc_endTime() {
		return auc_endTime;
	}
	public void setAuc_endTime(String auc_endTime) {
		this.auc_endTime = auc_endTime;
	}
	
	
	
}
