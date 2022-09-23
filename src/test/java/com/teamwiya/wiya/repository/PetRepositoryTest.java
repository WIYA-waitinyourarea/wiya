package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Pet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Test
    void 반려동물등록테스트() {
        Pet pet = new Pet();
        pet.setPetName("hihi");

        Pet pet1 = petRepository.save(pet);

        Optional<Pet> findPet = petRepository.findById(pet1.getPetId());
        assertThat(findPet.get().getPetName()).isEqualTo(pet1.getPetName());

    }



}