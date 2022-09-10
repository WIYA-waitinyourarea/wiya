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

import java.util.List;

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
        log.debug("booking={},{},{}",booking.getMember().getId(),booking.getHospital().getHosId(),booking.getBooId());
        bookingRepository.save(booking);
        return booking.getBooId();
    }

    @Transactional(readOnly = true)
    public int countCheck(Long memId, Long hosId){
        Member member = memberRepository.findById(memId).orElse(null);
        Hospital hospital = hospitalRepository.findOne(hosId);
        /*한개를 찾는 메소드여야 하는데, .getResultList()메소드로 인해 리스트 반환*/
        List<Booking> oneList = bookingRepository.findOne(memId, hosId);
        Booking booking = oneList.get(oneList.size()-1);//언박싱.. 좀 더 우아한 방법이 있을듯 // 혹은 2개가 조회시 예외 발생
        List<Booking> bookings = bookingRepository.checkRank(booking);
        return bookings.size();
    }
}
