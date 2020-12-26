package wg.vo;

import java.io.Serializable;

public class PreviewVO implements Serializable{
	private int prev_no;
	private int show_no;
	private String prevList_name;
	
	public int getPrev_no() {
		return prev_no;
	}
	public void setPrev_no(int prev_no) {
		this.prev_no = prev_no;
	}
	public int getShow_no() {
		return show_no;
	}
	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
	public String getPrevList_name() {
		return prevList_name;
	}
	public void setPrevList_name(String prevList_name) {
		this.prevList_name = prevList_name;
	}
	
	
}
