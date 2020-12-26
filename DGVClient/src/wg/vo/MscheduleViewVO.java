package wg.vo;

import java.io.Serializable;

public class MscheduleViewVO implements Serializable{
	private String theater_name;
	private String movie_name;
	private String show_time;
	private String show_end;
	private String dis_name;
	private int show_no;
	private int smovie_no;
	private String dis_id;
	private String movie_id;
	private String theater_id;
	private int movie_runningtime;
	
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	public String getShow_end() {
		return show_end;
	}
	public void setShow_end(String show_end) {
		this.show_end = show_end;
	}
	public String getDis_name() {
		return dis_name;
	}
	public void setDis_name(String dis_name) {
		this.dis_name = dis_name;
	}
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
	public String getDis_id() {
		return dis_id;
	}
	public void setDis_id(String dis_id) {
		this.dis_id = dis_id;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public int getMovie_runningtime() {
		return movie_runningtime;
	}
	public void setMovie_runningtime(int movie_runningtime) {
		this.movie_runningtime = movie_runningtime;
	}
	
	
	
}
