package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  
public interface GameRepository extends JpaRepository<Game, Integer>{ 
      
}
