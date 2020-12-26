 package wg.vo;

import java.io.Serializable;

public class ReviewViewVO implements Serializable {
   private int rev_no;
   private String book_id;
   private String bid_id;
   private String rev_content;
   private int rev_rating;
   private String rev_date;
   private String mem_id;
   public int getRev_no() {
      return rev_no;
   }
   public void setRev_no(int rev_no) {
      this.rev_no = rev_no;
   }
   public String getBook_id() {
      return book_id;
   }
   public void setBook_id(String book_id) {
      this.book_id = book_id;
   }
   public String getBid_id() {
      return bid_id;
   }
   public void setBid_id(String bid_id) {
      this.bid_id = bid_id;
   }
   public String getRev_content() {
      return rev_content;
   }
   public void setRev_content(String rev_content) {
      this.rev_content = rev_content;
   }
   public int getRev_rating() {
      return rev_rating;
   }
   public void setRev_rating(int rev_rating) {
      this.rev_rating = rev_rating;
   }
   public String getRev_date() {
      return rev_date;
   }
   public void setRev_date(String rev_date) {
      this.rev_date = rev_date;
   }
   public String getMem_id() {
      return mem_id;
   }
   public void setMem_id(String mem_id) {
      this.mem_id = mem_id;
   }
   
}