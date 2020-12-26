package wg.vo;

import java.io.Serializable;

public class BookMemberCntVO implements Serializable {
	private int male;
	private int female;
	private int age;
	
	
	
	public int getMale() {
		return male;
	}
	public void setMale(int male) {
		this.male = male;
	}
	public int getFemale() {
		return female;
	}
	public void setFemale(int female) {
		this.female = female;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
