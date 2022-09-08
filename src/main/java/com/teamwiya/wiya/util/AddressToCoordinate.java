package com.teamwiya.wiya.util;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.runtime.Coordinator;
import com.teamwiya.wiya.model.AddressCoo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//java.net 에 대한 학급을 필요로 함
//카카오 주소 API를 이용하여 좌표 반환
public class AddressToCoordinate {

    private static final String KAKAO_LOCAL_URL="http://dapi.kakao.com/v2/local/search/address.json?query="; // '카카오 로컬' REST API
    private static final String KAKAO_LOCAL_USER="KakaoAK 69675018b3453e8e2691782995b377d5"; // 카카오에서 발급받은 키


    public JSONObject coordinate(String address){
        String urlAddress;
        System.out.println("address = " + address);
        URL obj;
        HttpURLConnection conn;
        String inputLine;
        StringBuilder response;
        try {
            urlAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
            obj = new URL(KAKAO_LOCAL_URL+urlAddress); // API 통신을 위한 URL 객체 생성
            conn = (HttpURLConnection) obj.openConnection(); // API 통신을 위한 커넥션 오픈
            conn.setRequestMethod("GET"); // GET방식으로 요청
            conn.setRequestProperty("Authorization",KAKAO_LOCAL_USER); //카카오에서 발급받은 키를입력
            conn.setRequestProperty("content-type","application/json"); //json형식으로 요청 바디 보냄
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            //결과를 버퍼드리더에  담는 과정
            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("documents");
            JSONObject addrJson = (JSONObject) jsonArray.get(0);
            conn.disconnect(); // finally에 넣으면 뭔가 널포인트 예외 가능성.. -> 안날거같긴하지만 그냥 열렸을때만 닫자
            return addrJson;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject newAddressJson(JSONObject jsonObject){
       return ((JSONObject) jsonObject.get("road_address"));
    }

}
/*
{"documents":
    [{
        "address":
            {"address_name":"서울 서대문구 북가좌동 328-51"
            ,"b_code":"1141011900"
            ,"h_code":"1141072000"
            ,"main_address_no":"328"
            ,"mountain_yn":"N"
            ,"region_1depth_name":"서울"
            ,"region_2depth_name":"서대문구"
            ,"region_3depth_h_name":"북가좌2동"
            ,"region_3depth_name":"북가좌동"
            ,"sub_address_no":"51"
            ,"x":"126.911868515563"
            ,"y":"37.5823499880763"}
        ,"address_name":"서울 서대문구 북가좌동 328-51"
        ,"address_type":"REGION_ADDR"
        ,"road_address":
            {"address_name":"서울 서대문구 증가로29길 20-14"
            ,"building_name":""
            ,"main_building_no":"20"
            ,"region_1depth_name":"서울"
            ,"region_2depth_name":"서대문구"
            ,"region_3depth_name":"북가좌동"
            ,"road_name":"증가로29길"
            ,"sub_building_no":"14"
            ,"underground_yn":"N"
            ,"x":"126.911862642034"
            ,"y":"37.5823384509773"
            ,"zone_no":"03678"}
        ,"x":"126.911868515563"// 경도
        ,"y":"37.5823499880763"// 위도
    }]
,"meta":
    {"is_end":true
    ,"pageable_count":1
    ,"total_count":1}
}
*/

