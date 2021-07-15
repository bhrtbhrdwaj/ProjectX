package com.training.project.repository;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.project.model.Comment;
import com.training.project.model.Feature;
import com.training.project.model.Product;

@Service
public class ProjectService {
	@Autowired
	private ProductRepository product;
	@Autowired
	private CommentRepository comment;
	@Autowired
	private FeatureRepository feature;
	
	public Product createProduct(Product p) {
		return product.save(p);
	}
	public Comment createComment(Comment c, Product p) {
		c.setId(new Random().nextInt());
		c.setForProduct(p);
		c.setAt(System.currentTimeMillis());
		return comment.save(c);
	}
	
	public Comment getComment(int id) {
		return comment.findById(id).get();
	}
	
	public Product getProduct(int id) {
		return product.findById(id).get();
	}
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
		for(Product pr : product.findAll()) {
			products.add(pr);
		}
		return products;
	}
	
	public List<Comment> getCommentsForProductId(int id) {
		return comment.findByForProductId(id);
	}
	
	
	public void createFeatures(String[] features, Product forProduct) {
		Random r = new Random();
		for(String f: features) {
		Feature feature = new Feature(r.nextInt(), f);
		feature.setProduct(forProduct);
			this.feature.save(feature);
		}
	}
}
