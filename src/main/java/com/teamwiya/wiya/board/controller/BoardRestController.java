package com.teamwiya.wiya.board.controller;

import com.teamwiya.wiya.board.model.Comment;
import com.teamwiya.wiya.board.dto.CommentRequestDTO;
import com.teamwiya.wiya.board.repository.CommentRepository;
import com.teamwiya.wiya.board.service.CommentService;
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
