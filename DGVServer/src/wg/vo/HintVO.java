package wg.vo;

import java.io.Serializable;

public class HintVO implements Serializable{

	private int hint_no;
	private String hint_question;
	public int getHint_no() {
		return hint_no;
	}
	public void setHint_no(int hint_no) {
		this.hint_no = hint_no;
	}
	public String getHint_question() {
		return hint_question;
	}
	public void setHint_question(String hint_question) {
		this.hint_question = hint_question;
	}
	
	
}
