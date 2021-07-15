package com.training.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@Column(name = "comment_id")
	private int id;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private Product forProduct;
	
	
	@Column(name = "at")
	private long at;
	
	
	public Comment() {
		
	}
	
	public Comment(int id, String comment, String email, String name) {
		this.id = id;
		this.comment = comment;
		this.email = email;
		this.name = name;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public Product getForProduct() {
		return forProduct;
	}


	public void setForProduct(Product forProduct) {
		this.forProduct = forProduct;
	}


	public long getAt() {
		return at;
	}


	public void setAt(long at) {
		this.at = at;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", email=" + email + ", name=" + name + ", forProduct="
				+ forProduct + ", at=" + at + "]";
	}
}
