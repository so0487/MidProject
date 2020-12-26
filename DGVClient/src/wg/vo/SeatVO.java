package wg.vo;

import java.io.Serializable;

public class SeatVO implements Serializable{
	private String seat_id;
	private String theater_id;
	private String seat_name;
	private String seat_status;
	private int seat_price;
	
	
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
	public String getSeat_name() {
		return seat_name;
	}
	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}
	public String getSeat_status() {
		return seat_status;
	}
	public void setSeat_status(String seat_status) {
		this.seat_status = seat_status;
	}
	public int getSeat_price() {
		return seat_price;
	}
	public void setSeat_price(int seat_price) {
		this.seat_price = seat_price;
	}
	
	

	
	
}
