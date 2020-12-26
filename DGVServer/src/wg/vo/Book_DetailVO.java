package wg.vo;

import java.io.Serializable;

public class Book_DetailVO implements Serializable {
	private int bdet_no;
	private String book_id;
	private int sseat_no;
	
	
	public int getBdet_no() {
		return bdet_no;
	}
	public void setBdet_no(int bdet_no) {
		this.bdet_no = bdet_no;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public int getSseat_no() {
		return sseat_no;
	}
	public void setSseat_no(int sseat_no) {
		this.sseat_no = sseat_no;
	}
	
	
}
