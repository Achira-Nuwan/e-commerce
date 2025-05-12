package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Comments;

@Service
public interface CommentsService {
    List<Comments> getAllComments();

    Comments createComment(Comments comment);

    Comments getCommentById(Long commentId);

    Comments updateComment(Comments comment);

    void deleteComment(Long commentId);

    List<Comments> getCommentsByProductId(Long productId);
}
