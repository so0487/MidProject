package wg.vo;

import java.io.Serializable;

public class IssueViewVO implements Serializable{
	int issue_no;
	String cou_name;
	String mem_id;
	String issue_time;
	public int getIssue_no() {
		return issue_no;
	}
	public void setIssue_no(int issue_no) {
		this.issue_no = issue_no;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
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
