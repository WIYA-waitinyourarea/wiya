package com.teamwiya.wiya.service;

import com.teamwiya.wiya.dto.MarketDTO;
import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.model.Market;
import com.teamwiya.wiya.repository.MarketRepository;
import com.teamwiya.wiya.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
   // private final MarketDTO marketDTO;

    @Transactional
    public Long post(MarketDTO marketDTO) {
        Market market = Market.writeNewPost(
                marketDTO.getPostTitle(),
                marketDTO.getItemPrice(),
                marketDTO.getPostContent()
        );
        marketRepository.save(market);
        return market.getId();
    }

    public List<Market> findPosts() { //전체 글목록 조회
        return marketRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Market findOne(Long post_Id){
        return marketRepository.findOne(post_Id);
    }

}
