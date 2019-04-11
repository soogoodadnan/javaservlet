package com.journaldev.util;

import java.io.Serializable;

public class Images implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8187167802827722344L;
	private String name;
	private  byte[]  photo;
	private int id;
	
	public Images() {}
	public Images(String nm, byte[] photo, int i){
		this.name=nm;
		this.id=i;
		this.photo=photo;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public  byte[]  getPhoto() {
		return photo;
	}

	public int getId() {
		return id;
	}


}
