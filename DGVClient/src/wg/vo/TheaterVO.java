package wg.vo;

import java.io.Serializable;

public class TheaterVO implements Serializable {
	private String theater_id;
	private String theater_name;
	private int theater_numOfSeat;
	private String theater_status;
	
	
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public int getTheater_numOfSeat() {
		return theater_numOfSeat;
	}
	public void setTheater_numOfSeat(int theater_numOfSeat) {
		this.theater_numOfSeat = theater_numOfSeat;
	}
	public String getTheater_status() {
		return theater_status;
	}
	public void setTheater_status(String theater_status) {
		this.theater_status = theater_status;
	}
	
	
	
}
