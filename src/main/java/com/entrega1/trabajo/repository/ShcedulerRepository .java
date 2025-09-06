package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.Shceduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepository extends JpaRepository<Shceduler, Integer>{   
}
