package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.member.repository.MemberRepository;
import com.teamwiya.wiya.model.BooState;
import com.teamwiya.wiya.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepository {

    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final HospitalRepository hospitalRepository;

    public void save(Booking booking) {
        em.persist(booking);
    }

    public List<Booking> findOne(Long memId, Long hosId) {
        return  em.createQuery("select b " +
                "from Booking  b " +
                "where b.member.id = :memId " +
                "and b.hospital.hosId = :hosId " +
                "and b.booState = :booState ", Booking.class)
                .setParameter("memId", memId)
                .setParameter("hosId", hosId)
                .setParameter("booState", BooState.WAITING)
                .getResultList();

    }

    /*동적쿼리로 바꿔야될 듯*/
    public List<Booking> findByMember(Long memId) {
        return  em.createQuery("select b " +
                        "from Booking  b " +
                        "where b.member.id = :memId " +
                        "and b.booState = :booState ", Booking.class)
                .setParameter("memId", 1L)
                .setParameter("booState", BooState.WAITING)
                .getResultList();
    }

    /* count를 하는 방법을 알 수 있을까?*/
    public List<Booking> checkRank(Booking booking) {
        return em.createQuery("select b " +
                "from Booking b " +
                "where b.hospital.hosId = :hosId " +
                "and b.modifiedAt < :time " +
                "and b.booState = :booState", Booking.class)
                .setParameter("hosId", booking.getHospital().getHosId())
                .setParameter("time", booking.getModifiedAt())
                .setParameter("booState", BooState.WAITING)
                .getResultList();

    }
}
