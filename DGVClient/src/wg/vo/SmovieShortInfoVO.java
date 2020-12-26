package wg.vo;

import java.io.Serializable;

public class SmovieShortInfoVO implements Serializable{
	private String movie_name;
	private String movie_adult;
	private String movie_poster;
	
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_adult() {
		return movie_adult;
	}
	public void setMovie_adult(String movie_adult) {
		this.movie_adult = movie_adult;
	}
	public String getMovie_poster() {
		return movie_poster;
	}
	public void setMovie_poaster(String movie_poaster) {
		this.movie_poster = movie_poaster;
	}
	
	
}
