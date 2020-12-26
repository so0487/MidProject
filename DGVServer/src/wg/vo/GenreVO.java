package wg.vo;

import java.io.Serializable;

public class GenreVO implements Serializable{
	private String gen_id;
	private String gen_name;
	
	
	public String getGen_id() {
		return gen_id;
	}
	public void setGen_id(String gen_id) {
		this.gen_id = gen_id;
	}
	public String getGen_name() {
		return gen_name;
	}
	public void setGen_name(String gen_name) {
		this.gen_name = gen_name;
	}
	
	
}
