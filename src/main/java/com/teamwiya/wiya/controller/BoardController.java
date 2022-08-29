package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Board;
import com.teamwiya.wiya.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")//localhost:8090/board/write
    public String boardWriteForm(){
        return "boardWrite";
    }


    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){

        boardService.write(board);
        //System.out.println("내용 : " + board.getContent());

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list",boardService.boardList());
        return "boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model,Long id){

        model.addAttribute("board",boardService.boardView(id));

        return "boardView";
    }



}
