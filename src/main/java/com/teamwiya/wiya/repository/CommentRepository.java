package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
