package com.journaldev.util;

import java.io.Serializable;

public class Images implements Serializable {

	private static final long serialVersionUID = 8187167802827722344L;
	private String name;
	private  String  photo;
	private int id;
	
	public Images() {}
	public Images(String nm, String photo, int i){
		this.name=nm;
		this.id=i;
		this.photo=photo;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public String  getPhoto() {
		return photo;
	}

	public int getId() {
		return id;
	}


}
