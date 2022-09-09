package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.dto.BookingDTO;
import com.teamwiya.wiya.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("")
    public String booking(@RequestBody BookingDTO bookingDTO) {
        log.info("memId={}, hosId={}",bookingDTO.getMemId(),bookingDTO.getHosId());
        Long bookingId = bookingService.registerBooking(bookingDTO.getMemId(), bookingDTO.getHosId());
        return "현재 대기 인원을 리턴하는게 제일 아름다울 거 같은데..";
    }

}
