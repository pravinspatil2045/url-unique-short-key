package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class URL {

	@Id
	@GeneratedValue
	private Long url_id;
	private String url_address;
	private String unique_short_key;
	private int count;

	public Long getUrl_id() {
		return url_id;
	}

	public void setUrl_id(Long url_id) {
		this.url_id = url_id;
	}

	public String getUrl_address() {
		return url_address;
	}

	public void setUrl_address(String url_address) {
		this.url_address = url_address;
	}

	public String getUnique_short_key() {
		return unique_short_key;
	}

	public void setUnique_short_key(String unique_short_key) {
		this.unique_short_key = unique_short_key;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
