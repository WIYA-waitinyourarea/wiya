package com.teamwiya.wiya.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SigudongRepository {

    private final EntityManager em;

}
