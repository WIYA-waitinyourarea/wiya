package com.teamwiya.wiya.board.service;

import com.teamwiya.wiya.board.model.BoaImg;
import com.teamwiya.wiya.board.model.Board;
import com.teamwiya.wiya.board.repository.BoardRepository;
import com.teamwiya.wiya.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;



    @Transactional
    //글작성처리
    public void write(Board board, MultipartFile file){



        boardRepository.save(board);





        if(!file.isEmpty()){

            String orgName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String extension = orgName.substring(orgName.lastIndexOf("."));

            String savePath = System.getProperty("user.dir")+"/build/resources/main/static/images";

            File checkdir = new File(savePath);

            if(!checkdir.exists()){
                checkdir.mkdirs();
            }


            String saveName = uuid+extension;

            File myfile = new File(savePath,saveName);

            try {
                file.transferTo(myfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            BoaImg boaImg = BoaImg.builder()
                            .orgName(orgName)
                            .saveName(saveName)
                            .savePath(savePath)
                            .board(board).build();



            fileRepository.save(boaImg);

            //String savePath =
            
            //나는 repository.save로 할껀데?
            //Builder ??....뭐지???(객체를 생성하는 부분인건가?)
            //경로 지정 못하겠다....비극...


        }






    }

    //게시글 리스트 처리
    public List<Board> boardList(){
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"modifiedAt"));
    }

    //특정 게시글 불러오기
    public Board boardView(Long id){
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Long id){
        boardRepository.deleteById(id);
    }

}
