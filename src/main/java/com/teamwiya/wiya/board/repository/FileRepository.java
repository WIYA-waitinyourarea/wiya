package com.teamwiya.wiya.board.repository;


import com.teamwiya.wiya.board.model.BoaImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<BoaImg,Long> {
}
