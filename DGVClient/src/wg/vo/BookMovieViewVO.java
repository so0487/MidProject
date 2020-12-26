package wg.vo;

import java.io.Serializable;
import java.util.List;

public class BookMovieViewVO implements Serializable {
	
	private String book_id;			// 예매ID, bookmovie테이블
	private String book_time;		// 예매일시, bookmovie테이블
	private String movie_poster;	// 포스터, movie테이블
	private String movie_name;		// 영화제목, movie테이블
	private String show_time;		// 관람일시, mschedule테이블
	private String theater_name;	// 상영관이름, theater테이블
	private int pay_price;			// 총가격, pay테이블
	private String pay_refund;
	private int pay_no;
	private int book_qty;
	
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_time() {
		return book_time;
	}
	public void setBook_time(String book_time) {
		this.book_time = book_time;
	}
	public String getMovie_poster() {
		return movie_poster;
	}
	public void setMovie_poster(String movie_poster) {
		this.movie_poster = movie_poster;
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
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public String getPay_refund() {
		return pay_refund;
	}
	public void setPay_refund(String pay_refund) {
		this.pay_refund = pay_refund;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getBook_qty() {
		return book_qty;
	}
	public void setBook_qty(int book_qty) {
		this.book_qty = book_qty;
	}
	
	
	

	
}
