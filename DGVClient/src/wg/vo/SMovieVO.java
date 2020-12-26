package wg.vo;

import java.io.Serializable;

public class SMovieVO implements Serializable{
	private int smovie_no;
	private String theater_id;
	private String movie_id;
	public int getSmovie_no() {
		return smovie_no;
	}
	public void setSmovie_no(int smovie_no) {
		this.smovie_no = smovie_no;
	}
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	
	
	
}
