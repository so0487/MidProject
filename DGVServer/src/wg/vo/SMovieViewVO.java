package wg.vo;

import java.io.Serializable;

public class SMovieViewVO implements Serializable{
	private int smovie_no;
	private String theater_name;
	private String theater_id;
	private String movie_name;
	private String movie_id;
	private String movie_release;
	private String movie_end;
	
	public int getSmovie_no() {
		return smovie_no;
	}
	public void setSmovie_no(int smovie_no) {
		this.smovie_no = smovie_no;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_release() {
		return movie_release;
	}
	public void setMovie_release(String movie_release) {
		this.movie_release = movie_release;
	}
	public String getMovie_end() {
		return movie_end;
	}
	public void setMovie_end(String movie_end) {
		this.movie_end = movie_end;
	}
	
	
	
	
}
