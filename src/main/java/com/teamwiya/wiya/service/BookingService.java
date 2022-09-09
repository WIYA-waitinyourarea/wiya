package com.teamwiya.wiya.service;

import com.teamwiya.wiya.model.Booking;
import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.repository.BookingRepository;
import com.teamwiya.wiya.repository.HospitalRepository;
import com.teamwiya.wiya.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HospitalRepository hospitalRepository;
    private final MemberRepository memberRepository;

    public Long registerBooking(Long memId, Long hosId){
        Member member = memberRepository.findById(memId).orElse(null);
        Hospital hospital = hospitalRepository.findOne(hosId);
        Booking booking = Booking.createBooking(member, hospital);
        log.info("booking={},{},{}",booking.getMember().getId(),booking.getHospital().getHosId(),booking.getBooId());
        bookingRepository.save(booking);
        return booking.getBooId();
    }

}
