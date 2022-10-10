package com.teamwiya.wiya.hospital.model;

import com.teamwiya.wiya.util.AddressToCoordinate;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import javax.persistence.Embeddable;

@Slf4j
@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String sido; //서울
    private String siqungu; //서대문구
    private String bname; //북가좌동
    private String jibunAddress; //서울 서대문구 북가좌동 328-51
    private String roadAddress; //서울 서대문구 증가로29길 20-14
    private String sangse;
    private double x;
    private double y;
    private Long bCode;

    public static Address createAddress (String jibunAddress, String sangse) {
        AddressToCoordinate addressToCoordinate = new AddressToCoordinate();
        JSONObject jsonObject = addressToCoordinate.coordinate(jibunAddress);
        JSONObject roadJoso = addressToCoordinate.newAddressJson(jsonObject);
        Long bCode = addressToCoordinate.bCode(jsonObject);
        return Address.builder()
                .sido((String) roadJoso.get("region_1depth_name"))
                .siqungu((String) roadJoso.get("region_2depth_name"))
                .bname((String) roadJoso.get("region_3depth_name"))
                .jibunAddress(jibunAddress)
                .roadAddress((String) roadJoso.get("address_name"))
                .sangse(sangse)
                .x(Double.parseDouble((String) roadJoso.get("x")))
                .y(Double.parseDouble((String) roadJoso.get("y")))
                .bCode(bCode)
                .build();
    }
}
