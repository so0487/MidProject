package wg.vo;

import java.io.Serializable;
import java.util.List;

public class CartSnackSetVO implements Serializable{
	
	private String set_id;
	private int num;
	private String mem_id;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	private String set_name;
	private int set_price;
	private String set_photo;
	
	
	public String getSet_id() {
		return set_id;
	}
	public void setSet_id(String set_id) {
		this.set_id = set_id;
	}
	public String getSet_name() {
		return set_name;
	}
	public void setSet_name(String set_name) {
		this.set_name = set_name;
	}
	public int getSet_price() {
		return set_price;
	}
	public void setSet_price(int set_price) {
		this.set_price = set_price;
	}
	public String getSet_photo() {
		return set_photo;
	}
	public void setSet_photo(String set_photo) {
		this.set_photo = set_photo;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
	
}