package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Board;
import com.teamwiya.wiya.repository.CommentRepository;
import com.teamwiya.wiya.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final CommentRepository commentRepository;

    @GetMapping("/board/new")//localhost:8090/board/write
    public String boardWriteForm(){
        return "board/newform";
    }


    @PostMapping("/board/new")
    public String boardWritePro(Board board, Model model, @RequestParam("myfile") MultipartFile file){
                                //@ModelAttribute("board") Board board
                                //내가 파라미터(name 속성으로 넘긴것들)로 넘긴 것들을 set 해주겠다.

                                //Spring mvc 사용 이유  view(랜더링 하는곳)단이랑 controller단이랑 나누겠다.
                                //뺵단에서 정보를 담아 서 뷰로 가는 객체가    Model    객체이다.
                                //(Model 객체는 스프링에서 생성에서 넣어주는거다.)



        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(file);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        //파일 서비스를 따로 만들어야 할꺼같은데?
        // 만들어야 한다. (O || X)

        //주갲전도?
        // 1반 백경환, 이채영, 함세강
        // 1반을 만들고 백경환,이채영,함세강 맞냐?
        //백경환,이채영,함세강을 만들고  1반이라고 명시하는게 맞냐?
        //주객이 전도되면 안된다.
        //   게시글을 올리는 하나의 트랜잭션 과정에서 게시글 객체를 만들고 이미지 객체를 만든다. (이미지를 업로드 하고 게시글을 작성하는거다)
        //만약에 게시글 올리다가 삑나면 이미지도 내려가야 하지 않겄니?
        //
        //게시글에는 이미지 컬럼이 없다...잘 생각해 봐라...
        //그러면 상직적으로 순서가 게시글을 만들고 이미지 객체를 만드는게 이해가 될것이다.
        //레코드가 객체이다.

        //바이트 코드로 넘어온 file을 다시 저장 하는 과정이 필요한거 아닌가?(일단 이게 맞는건가?)
        //=> 스프링이 아름답게 파싱해 준다.
        //순서는 뭐가 맞는걸까....


        //파일을 저장하고....경로가 없으면 경로를 만들고 거기다가 저장?
        //경로가 없으면 경로를 만들고 거기다가 변환된 파일을 저장?

        boardService.write(board,file);
        //System.out.println("내용 : " + board.getContent());

        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "board/message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list",boardService.boardList());
        return "board/list";
    }

    @GetMapping("/board/detail")
    public String boardView(Model model, @RequestParam Long id){

        model.addAttribute("board",boardService.boardView(id));

        return "board/detail";
    }

    @GetMapping("/board/delete")//post로 수정
    public String boaedDelete(@RequestParam Long id){

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/edit/{id}")
    public String boardModify(@PathVariable Long id, Model model){
        model.addAttribute("board",boardService.boardView(id));
        return "board/editform";
    }

    @PostMapping("/board/edit/{id}")
    public String boardUpdate(@PathVariable Long id,Board board){
        Board boardTemp = boardService.boardView(id);       //기존에 있던 내용 가져오기
        boardTemp.setContent(board.getContent());

       //boardService.write(boardTemp);
        //이거 주석 해제해야한다.

        return "redirect:/board/list";
    }

}
