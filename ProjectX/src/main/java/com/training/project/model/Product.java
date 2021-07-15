package com.training.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "product_id")
	private int id;
	
	@Column(name = "name")
	private String productName;
	
	@Column(name = "images")
	private String images;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "forProduct")
	private List<Comment> comments;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "product")
	private List<Feature> features;
	
	public Product() {}
	

	public Product(int id, String productName) {
		this.id = id;
		this.productName = productName;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}
	
	@Transient
    public String getPhotosImagePath() {
        if (this.images == null || this.id == 0) return null;
         
        return "products/" + id + "/" + "image";
    }


	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", images=" + images + ", comments=" + comments
				+ "]";
	}

}
