package com.teamwiya.wiya.member.repository;

import com.teamwiya.wiya.member.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
