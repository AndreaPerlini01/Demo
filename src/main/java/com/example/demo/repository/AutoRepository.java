package com.example.demo.repository;

import com.example.demo.entities.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface AutoRepository extends JpaRepository<Auto, String> {

    Set<Auto> findAutoByHp(int hp);

    Set<Auto> findAutoByName(String name);

}
