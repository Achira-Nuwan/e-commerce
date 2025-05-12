package com.example.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String comment;
    private Long productId;
    private String userEmail;
    private Double rating;
}
