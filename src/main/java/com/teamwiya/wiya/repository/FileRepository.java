package com.teamwiya.wiya.repository;


import com.teamwiya.wiya.model.BoaImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface FileRepository extends JpaRepository<BoaImg,Long> {
}
