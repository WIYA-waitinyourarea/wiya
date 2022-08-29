package com.teamwiya.wiya.service;

import com.teamwiya.wiya.model.Board;
import com.teamwiya.wiya.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //글작성처리
    public void write(Board board){

        boardRepository.save(board);
    }

    //게시글 리스트 처리
    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    //특정 게시글 불러오기
    public Board boardView(Long id){
        return boardRepository.findById(id).get();
    }

}
