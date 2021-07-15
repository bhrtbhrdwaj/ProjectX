package com.training.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.project.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer>{

	public List<Comment> findByForProductId(int id);
}
