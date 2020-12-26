package wg.vo;

import java.io.Serializable;

public class NoticeVO implements Serializable {
	private int not_no;
	private String not_writer;
	private String not_title;
	private String not_content;
	private String not_time;
	
	public int getNot_no() {
		return not_no;
	}
	public void setNot_no(int not_no) {
		this.not_no = not_no;
	}
	public String getNot_writer() {
		return not_writer;
	}
	public void setNot_writer(String not_writer) {
		this.not_writer = not_writer;
	}
	public String getNot_title() {
		return not_title;
	}
	public void setNot_title(String not_title) {
		this.not_title = not_title;
	}
	public String getNot_content() {
		return not_content;
	}
	public void setNot_content(String not_content) {
		this.not_content = not_content;
	}
	public String getNot_time() {
		return not_time;
	}
	public void setNot_time(String not_time) {
		this.not_time = not_time;
	}
	
	
	
}
