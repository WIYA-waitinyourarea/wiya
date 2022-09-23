package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
