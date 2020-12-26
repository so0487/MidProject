package wg.vo;

import java.io.Serializable;

public class MemberVO implements Serializable {

	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_gen;
	private String mem_bir;
	private String mem_email;
	private String mem_tel;
	private String mem_addr;
	private int hint_no;
	private String mem_answer;
	private int mem_lev;
	private String mem_state;
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_gen() {
		return mem_gen;
	}
	public void setMem_gen(String mem_gen) {
		this.mem_gen = mem_gen;
	}
	public String getMem_bir() {
		return mem_bir;
	}
	public void setMem_bir(String mem_bir) {
		this.mem_bir = mem_bir;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public int getHint_no() {
		return hint_no;
	}
	public void setHint_no(int hint_no) {
		this.hint_no = hint_no;
	}
	public String getMem_answer() {
		return mem_answer;
	}
	public void setMem_answer(String mem_answer) {
		this.mem_answer = mem_answer;
	}
	public int getMem_lev() {
		return mem_lev;
	}
	public void setMem_lev(int mem_lev) {
		this.mem_lev = mem_lev;
	}
	public String getMem_state() {
		return mem_state;
	}
	public void setMem_state(String mem_state) {
		this.mem_state = mem_state;
	}
	
	

}
