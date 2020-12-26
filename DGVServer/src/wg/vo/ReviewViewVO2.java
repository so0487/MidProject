package wg.vo;

import java.io.Serializable;

public class ReviewViewVO2 implements Serializable {
	private String mem_name;
	private String rev_content;
	private double rev_rating;
	private String rev_date;

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getRev_content() {
		return rev_content;
	}

	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}

	public double getRev_rating() {
		return rev_rating;
	}

	public void setRev_rating(double rev_rating) {
		this.rev_rating = rev_rating;
	}

	public String getRev_date() {
		return rev_date;
	}

	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}
	
}
