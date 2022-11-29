package com.teamwiya.wiya.hospital.dto;

import lombok.Data;

@Data
public class PagingDTO {

    private int page = 1; // 기본값 1
    private int limit = 7;
    private int offset = 0;
    private int pages = 10;

    public void updateOffset() {
        offset = (page-1)*limit;
    }
}
