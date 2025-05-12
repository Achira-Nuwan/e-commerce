package com.example.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.dto.CommentRequestDto;
import com.example.e_commerce.entities.Comments;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.entities.User;
import com.example.e_commerce.service.CommentsService;
import com.example.e_commerce.service.ProductService;
import com.example.e_commerce.service.UserService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.ok(commentsService.getAllComments());
    }

    @GetMapping("/{commentId}")

    public ResponseEntity<?> getCommentById(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentsService.getCommentById(commentId));
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto commentDto) {

        User user = userService.getUserByEmail(commentDto.getUserEmail());
        Product product = productService.getProductById(commentDto.getProductId());
        if (user == null || product == null) {
            return ResponseEntity.badRequest().body("User or Product not found");
        }

        Comments comment = new Comments();
        comment.setCommentText(commentDto.getComment());
        comment.setUser(user);
        comment.setProduct(product);
        comment.setRating(commentDto.getRating());
        return ResponseEntity.ok(commentsService.createComment(comment));
    }

    @PutMapping("/{commentId}")

    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentDto) {
        Comments existingComment = commentsService.getCommentById(commentId);
        if (existingComment == null) {
            return ResponseEntity.notFound().build();
        }

        existingComment.setCommentText(commentDto.getComment());
        existingComment.setUser(userService.getUserByEmail(commentDto.getUserEmail()));
        existingComment.setProduct(productService.getProductById(commentDto.getProductId()));
        existingComment.setRating(commentDto.getRating());
        if (existingComment.getUser() == null || existingComment.getProduct() == null) {
            return ResponseEntity.badRequest().body("User or Product not found");
        }
        return ResponseEntity.ok(commentsService.updateComment(existingComment));
    }

}
