package com.teamwiya.wiya.hospital.dto;

import lombok.Data;

@Data
public class PagingDTO {

    private int page = 1;
    private int limit = 4;
    private int offset = 0;

    public void updateOffset() {
        offset = (page-1)*limit;
    }
}
