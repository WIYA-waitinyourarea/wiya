package com.teamwiya.wiya.board.repository;

import com.teamwiya.wiya.board.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
