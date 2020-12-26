package wg.vo;

import java.io.Serializable;

public class QuestionVO implements Serializable {
		private int question_no;
		private String mem_id;
		private String question_title;
		private String question_content;
		private String question_time;
		
		public int getQuestion_no() {
			return question_no;
		}
		public void setQuestion_no(int question_no) {
			this.question_no = question_no;
		}
		public String getMem_id() {
			return mem_id;
		}
		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}
		public String getQuestion_title() {
			return question_title;
		}
		public void setQuestion_title(String question_title) {
			this.question_title = question_title;
		}
		public String getQuestion_content() {
			return question_content;
		}
		public void setQuestion_content(String question_content) {
			this.question_content = question_content;
		}
		public String getQuestion_time() {
			return question_time;
		}
		public void setQuestion_time(String question_time) {
			this.question_time = question_time;
		}
		
		
		

}
