package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    // custom queries can be added here
}
