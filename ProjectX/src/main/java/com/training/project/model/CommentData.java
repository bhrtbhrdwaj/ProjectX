package com.training.project.model;

public class CommentData {
private String comment;
private String email;
private String name;

public CommentData() {}

public CommentData(String comment, String name, String email) {
	this.comment = comment;
	this.email = email;
	this.name = name;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "CommentData [comment=" + comment + ", email=" + email + ", name=" + name + "]";
}
}
