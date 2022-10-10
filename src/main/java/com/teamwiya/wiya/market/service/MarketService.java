package com.teamwiya.wiya.market.service;

import com.teamwiya.wiya.market.dto.MarketDTO;
import com.teamwiya.wiya.market.model.Market;
import com.teamwiya.wiya.market.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
   // private final MarketDTO marketDTO;

    @Transactional
    public void post(MarketDTO marketDTO, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "/out/production/resources/static/marketImgs"; //저장할 경로 지정하기

        File existChk = new File(projectPath);
        if(!existChk.exists()) existChk.mkdirs(); //디렉토리 없다면 만들어줌.
        UUID uuid = UUID.randomUUID(); //식별자 uuid, 랜덤으로 만들어준다. 파일이름에 붙일 랜덤 이름을 설정함.

        String fileName = uuid +"_"+file.getOriginalFilename(); //랜덤이름 + 원래 파일명

        File saveFile = new File(projectPath, fileName); //파일에 경로를 넣어주고, 이름 설정해 줄 것이다
        file.transferTo(saveFile);

        marketDTO.setFilename(fileName);
        marketDTO.setFilepath("/marketImgs/"+fileName);

        Market market = Market.writeNewPost(
                marketDTO.getPostTitle(),
                marketDTO.getItemPrice(),
                marketDTO.getPostContent(),
                marketDTO.getFilename(),
                marketDTO.getFilepath()
        );

        marketRepository.save(market);
        //Long ---- return market.getId();
    }

    public List<Market> findPosts() { //전체 글목록 조회
        return marketRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Market findOne(Long post_Id){
        return marketRepository.findOne(post_Id);
    }


    //특정게시글 삭제
    @Transactional
    public void marketDelete(Long id) {
        marketRepository.deleteById(id);
    }




}
