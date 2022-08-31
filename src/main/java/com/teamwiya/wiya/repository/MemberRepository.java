package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemMail(String email);


}
