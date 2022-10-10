package com.teamwiya.wiya.board.repository;

import com.teamwiya.wiya.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

}





