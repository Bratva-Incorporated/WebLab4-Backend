package com.porunit.l4.repositories;

import com.porunit.l4.data.Shot;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShotRepository extends JpaRepository<Shot, Long> {
    @Transactional
    void deleteAllByUsername(String username);
    List<Shot> findAllByUsername(String username);
}
