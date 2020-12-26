package wg.vo;

import java.io.Serializable;

public class MScheduleVO implements Serializable {
	private int show_no;
	private int smovie_no;
	private String dis_no;
	private String show_beginTime;
	
	
	public int getShow_no() {
		return show_no;
	}
	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
	public int getSmovie_no() {
		return smovie_no;
	}
	public void setSmovie_no(int smovie_no) {
		this.smovie_no = smovie_no;
	}
	public String getDis_no() {
		return dis_no;
	}
	public void setDis_no(String dis_no) {
		this.dis_no = dis_no;
	}
	public String getShow_beginTime() {
		return show_beginTime;
	}
	public void setShow_beginTime(String show_beginTime) {
		this.show_beginTime = show_beginTime;
	}
	
	
}
