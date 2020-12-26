package wg.vo;

import java.io.Serializable;

public class IssueCouponVO implements Serializable{
	private int issue_no;
	private String cou_id;
	private String mem_id;
	private String issue_time;
	private String issue_use;
	
	public int getIssue_no() {
		return issue_no;
	}
	public void setIssue_no(int issue_no) {
		this.issue_no = issue_no;
	}
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getIssue_time() {
		return issue_time;
	}
	public void setIssue_time(String issue_time) {
		this.issue_time = issue_time;
	}
	
	
	
	
}
