package wg.vo;

import java.io.Serializable;

public class OnOffViewVO implements Serializable {
	
	private int on_no;
	private String mem_id;
	private String mem_name;
	private String on_time;
	private String off_time;
	
	
	public int getOn_no() {
		return on_no;
	}
	public void setOn_no(int on_no) {
		this.on_no = on_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getOn_time() {
		return on_time;
	}
	public void setOn_time(String on_time) {
		this.on_time = on_time;
	}
	public String getOff_time() {
		return off_time;
	}
	public void setOff_time(String off_time) {
		this.off_time = off_time;
	}
	
	
	
	
	
}
