package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.BooState;
import com.teamwiya.wiya.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepository {

    private EntityManager em;
    private MemberRepository memberRepository;
    private HospitalRepository hospitalRepository;

    public void save(Booking booking) {
        em.persist(booking);
    }

    public List<Booking> findOne(Long memberId, Long hospitalId) {
        return  em.createQuery("select b " +
                "from Booking  b " +
                "where b.member.id = :memberId " +
                "and b.hospital.hosId = :hospitalId " +
                "and b.booState = 1 ", Booking.class)
                .setParameter("memberId", 1L)
                .setParameter("hospitalId", hospitalId)
                .getResultList();

    }

    /*동적쿼리로 바꿔야될 듯*/
    public List<Booking> findByMember(Long memberId) {
        return  em.createQuery("select b " +
                        "from Booking  b " +
                        "where b.member.id = :memberId " +
                        "and b.booState = 1 ", Booking.class)
                .setParameter("memberId", 1L)
                .getResultList();
    }

}
