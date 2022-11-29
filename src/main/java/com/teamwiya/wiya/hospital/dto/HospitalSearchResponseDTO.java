package com.teamwiya.wiya.hospital.dto;

import com.teamwiya.wiya.hospital.model.Hospital;
import com.teamwiya.wiya.hospital.model.Sigudong;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HospitalSearchResponseDTO {
    private Long totalResultCount;
    private List<Sigudong> sidos = new ArrayList<>();
    private List<HospitalDetailDTO> searchResult = new ArrayList<>();

    public void hospitalEntityToDto(List<Hospital> hopitals) {
        for (Hospital hopital : hopitals) {
            searchResult.add(new HospitalDetailDTO(hopital));
        }
    }
}
