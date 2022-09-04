package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Board;
import com.teamwiya.wiya.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")//localhost:8090/board/write
    public String boardWriteForm(){
        return "forms";
    }


    @PostMapping("/board/write")
    public String boardWritePro(Board board, Model model){

        boardService.write(board);
        //System.out.println("내용 : " + board.getContent());

        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list",boardService.boardList());
        return "boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, @RequestParam Long id){

        model.addAttribute("board",boardService.boardView(id));

        return "boardDetailView";
    }

    @GetMapping("/board/delete")
    public String boaedDelete(@RequestParam Long id){

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable Long id, Model model){
        model.addAttribute("board",boardService.boardView(id));
        return "formModify";
    }

    @PostMapping("/board/modify/{id}")
    public String boardUpdate(@PathVariable Long id,Board board){
        Board boardTemp = boardService.boardView(id);       //기존에 있던 내용 가져오기
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        return "redirect:/board/list";
    }

}
