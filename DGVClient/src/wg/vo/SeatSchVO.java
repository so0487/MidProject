package wg.vo;

import java.io.Serializable;

public class SeatSchVO implements Serializable {
	private int sseat_no;
	private String seat_id;
	private String theater_id;
	private int show_no;
	private String sseat_status;
	
	
	public int getSseat_no() {
		return sseat_no;
	}
	public void setSseat_no(int sseat_no) {
		this.sseat_no = sseat_no;
	}
	public String getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
	}
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public int getShow_no() {
		return show_no;
	}
	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
	public String getSseat_status() {
		return sseat_status;
	}
	public void setSseat_status(String sseat_status) {
		this.sseat_status = sseat_status;
	}
	
	
	
}
