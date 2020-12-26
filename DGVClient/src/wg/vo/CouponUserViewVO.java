package wg.vo;

import java.io.Serializable;

public class CouponUserViewVO implements Serializable{
	private String cou_id;
	private String cou_name;
	private int cou_sale;
	private String cou_time;
	private String issue_time;
	private int issue_no;
	private String issue_use;
	
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
	public String getIssue_time() {
		return issue_time;
	}
	public void setIssue_time(String issue_time) {
		this.issue_time = issue_time;
	}
	public int getIssue_no() {
		return issue_no;
	}
	public void setIssue_no(int issue_no) {
		this.issue_no = issue_no;
	}
	public String getIssue_use() {
		return issue_use;
	}
	public void setIssue_use(String issue_use) {
		this.issue_use = issue_use;
	}
	
	
	
	
}
