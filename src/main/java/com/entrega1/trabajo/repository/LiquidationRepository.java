package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.Liquidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquidationRepository extends JpaRepository<Liquidation, Integer> {
    
}
