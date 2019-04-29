package com.journaldev.util;

import java.io.Serializable;

public class Comments implements Serializable {

	private static final long serialVersionUID = -8769387316695425095L;
	private String comments;
	private String userName;
	private int id;
	private int imageId;
	private int userId;
	private int raiting;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Comments() {}
	
	

	public Comments(String comments, String userName, int id, int imageId, int userId,int raiting) {
		super();
		this.comments = comments;
		this.userName = userName;
		this.id = id;
		this.imageId = imageId;
		this.userId = userId;
		this.raiting = raiting;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRaiting() {
		return raiting;
	}

	public void setRaiting(int raiting) {
		this.raiting = raiting;
	}

	

}
