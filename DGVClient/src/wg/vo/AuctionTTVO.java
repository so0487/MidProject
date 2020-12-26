package wg.vo;

import java.io.Serializable;

public class AuctionTTVO implements Serializable{
	private String theater_name;
	private String show_time;
	
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	
	
	
}
