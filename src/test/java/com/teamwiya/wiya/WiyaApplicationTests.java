package com.teamwiya.wiya;


import com.teamwiya.wiya.model.Board;
import com.teamwiya.wiya.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
public class WiyaApplicationTests {

	@Autowired
	BoardRepository boardRepository;

	@Transactional
	@Test
	public void contextLoads() {

		Board memo = Board.builder()
				.content("6번쨰 게시물")
				.build();

		Board board = boardRepository.save(memo);

		Optional<Board> result = boardRepository.findById(board.getId());

		Assertions.assertThat(result.get().getId()).isEqualTo(board.getId());


	}

	@Test
	public void delete() {

		// 1. 게시글 조회
		Board entity = boardRepository.findById((long) 2).get();

		// 2. 게시글 삭제
		boardRepository.delete(entity);
	}

	@Test
	public void select(){
		Board memo = Board.builder()
				.content("3번쨰 게시물")
				.build();

		Optional<Board> findBoard = boardRepository.findById(memo.getId());

	}



}








