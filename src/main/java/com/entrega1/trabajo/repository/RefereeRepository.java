package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Integer>{   
}
