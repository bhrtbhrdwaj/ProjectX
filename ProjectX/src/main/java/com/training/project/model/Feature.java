package com.training.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "feature")
public class Feature {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "feature_id")
	private int id;
	
	@Column(name ="feature")
	private String feature;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Feature() {
		
	}

	public Feature(int id,String feature) {
		this.feature = feature; this.id = id;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Feature [id=" + id + ", feature=" + feature + ", product=" + product + "]";
	}
	
	

}
