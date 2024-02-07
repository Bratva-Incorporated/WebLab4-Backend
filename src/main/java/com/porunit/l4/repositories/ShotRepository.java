package com.porunit.l4.repositories;

import com.porunit.l4.data.Shot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShotRepository extends JpaRepository<Shot, Long> {

}
