package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Tournament;
import com.entrega1.trabajo.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.time.LocalDate;


@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> findById(Integer id) {
        return tournamentRepository.findById(id);
    }

    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public void deleteById(Integer id) {
        tournamentRepository.deleteById(id);
    }

    public List<Tournament> activeTournaments() {
        List<Tournament> tournaments = new ArrayList<>();

        LocalDate today = LocalDate.now();
        for (Tournament tournament : tournamentRepository.findAll()) {
            if (tournament.getStartDate().isBefore(today) && tournament.getEndDate().isAfter(today)) { 
                
                tournaments.add(tournament);
            }
        }

        return tournaments;
    }
}
