package com.teamwiya.wiya.board.service;

import com.teamwiya.wiya.board.model.Board;
import com.teamwiya.wiya.board.model.Comment;
import com.teamwiya.wiya.board.dto.CommentRequestDTO;
import com.teamwiya.wiya.board.repository.BoardRepository;
import com.teamwiya.wiya.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Comment addComment(CommentRequestDTO dto) {
        //log.info("boaId={}", dto.getBoardId());
        Optional<Board> findBoard = boardRepository.findById(dto.getBoardId());
        //java 8문법
        Comment comment = new Comment(dto, findBoard.orElse(null));// 엔티티를 생성
        commentRepository.save(comment);
        return comment;
    }




}
