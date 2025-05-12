package com.example.e_commerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Comments;
import com.example.e_commerce.repositories.CommentsRepository;
import com.example.e_commerce.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    
    @Override
    public Comments getCommentById(Long commentId) {
        Comments comment = commentsRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
        return comment;
    }

    @Override
    public Comments createComment(Comments comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public Comments updateComment(Comments comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comments existingComment = commentsRepository.findById(commentId).orElse(null);
        if (existingComment == null) {
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
        commentsRepository.delete(existingComment);
    }

    @Override
    public List<Comments> getCommentsByProductId(Long productId) {
        List<Comments> comments = commentsRepository.findByProductId(productId);
        if (comments.isEmpty()) {
            throw new RuntimeException("No comments found for product with id: " + productId);
        }
        return comments;
    }

}
