package com.teamwiya.wiya.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;


@Slf4j
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HosImg extends TimeStamped{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long himId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hosId") //hosImg 테이블의 컬럼명
    private Hospital hospital; //hospital PK
    private String himPath;
    private boolean himMain;
    private String himOrigin;


    /*== 생성 메소드 ==*/
    /**
     * 메소드가 너무 길다 일단 좀 나눌 필요가 있다. 이기능들이 다 여기있는게 옳은건지 좀 고민해보기
     * @param hospital
     * @param file
     * @return
     */
    public static HosImg createHosImg(Hospital hospital, MultipartFile file){
        String path = file.getOriginalFilename();
        String savedPath = System.getProperty("user.dir") + "/out/production/resources/static/images/upload";
        File existChk = new File(savedPath);
        if(!existChk.exists()) existChk.mkdirs(); //메이크 디렉토리
        String savedFile = UUID.randomUUID().toString().replaceAll("-", "") // 서버에 저장할 파일 명으로 쓸 UUID (중복방지)
                + path.substring(path.lastIndexOf(".")); // 확장자 명 추출
/*        log.info("filePath={}", savedPath);
        log.info("fileFile={}", savedFile);*/
        try {
            file.transferTo(new File(savedPath, savedFile)); // 서버에 파일로서 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HosImg.builder()
                .himPath("/images/upload/" + savedFile)
                .hospital(hospital)
                .himOrigin(path)
                .build();
    }

}
