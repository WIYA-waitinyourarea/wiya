package com.teamwiya.wiya.model;

import com.teamwiya.wiya.dto.HospitalSaveForm;
import com.teamwiya.wiya.util.AddressToCoordinate;
import lombok.*;
import org.json.simple.JSONObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String sido; //서울
    private String siqungu; //서대문구
    private String bname;
    private String jibunAddress; //서울 서대문구 북가좌동 328-51
    private String roadAddress; //서울 서대문구 증가로29길 20-14
    private String sangse;
    private double x;
    private double y;

    public static Address createAddress (String jibunAddress, String sangse) {
        AddressToCoordinate addressToCoordinate = new AddressToCoordinate();
        JSONObject jsonObject = addressToCoordinate.coordinate(jibunAddress);
        JSONObject roadJoso = addressToCoordinate.newAddressJson(jsonObject);
        return Address.builder()
                .sido((String) roadJoso.get("region_1depth_name"))
                .siqungu((String) roadJoso.get("region_2depth_name"))
                .bname((String) roadJoso.get("region_3depth_name"))
                .jibunAddress(jibunAddress)
                .roadAddress((String) roadJoso.get("address_name"))
                .sangse(sangse)
                .x(Double.parseDouble((String) jsonObject.get("x")))
                .y(Double.parseDouble((String) jsonObject.get("y")))
                .build();
    }
}
