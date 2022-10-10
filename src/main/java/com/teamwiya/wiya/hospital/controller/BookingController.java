package com.teamwiya.wiya.hospital.controller;

import com.teamwiya.wiya.hospital.dto.BookingSaveRequest;
import com.teamwiya.wiya.hospital.repository.BookingRepository;
import com.teamwiya.wiya.hospital.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/booking")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @PostMapping("")
    public String booking(@RequestBody BookingSaveRequest bookingDTO,
                          HttpServletRequest request) {
        log.info("memId={}, hosId={}",bookingDTO.getMemId(),bookingDTO.getHosId());
        //회원 아이디는 세션에서 받아와야함
        //HttpSession session = request.getSession();
        //Member member = (Member) session.getAttribute("member");
        //Long memId = member.getId();
        Long bookingId = bookingService.registerBooking(bookingDTO.getMemId(), bookingDTO.getHosId());
        return "현재 대기 인원을 리턴하는게 제일 아름다울 거 같은데..";
    }

    @GetMapping("/count")
    public String count(@RequestBody BookingSaveRequest bookingDTO) {
        //회원 아이디는 세션에서 받아와야함
        //HttpSession session = request.getSession();
        //Member member = (Member) session.getAttribute("member");
        //Long memId = member.getId();
        int count = bookingService.countCheck(bookingDTO.getMemId(), bookingDTO.getHosId());

        return count + "명 대기 중 ..";
    }

}
