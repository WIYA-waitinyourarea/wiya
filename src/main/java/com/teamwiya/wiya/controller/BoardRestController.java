package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Comment;
import com.teamwiya.wiya.dto.CommentRequestDTO;
import com.teamwiya.wiya.repository.CommentRepository;
import com.teamwiya.wiya.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardRestController {


    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/comment/enroll")
    public Comment enrollComment(@RequestBody CommentRequestDTO requestDTO){
        /*Comment comment = new Comment(requestDTO);
        commentRepository.save(comment);*/
        /*return comment;*/
        return commentService.addComment(requestDTO);
    }




}
