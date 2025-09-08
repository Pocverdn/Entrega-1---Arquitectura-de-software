package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Game;
import com.entrega1.trabajo.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository juegoRepository;

    public GameService(GameRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    public List<Game> findAll() {
        return juegoRepository.findAll();
    }

    public Optional<Game> findById(Integer id) {
        return juegoRepository.findById(id);
    }

    public Game save(Game juego) {
        return juegoRepository.save(juego);
    }

    public void deleteById(Integer id) {
        juegoRepository.deleteById(id);
    }    

    public List<Game> gameFuture(){
        List<Game> games = new ArrayList<>();

        LocalDate today = LocalDate.now();

        for (Game game : juegoRepository.findAll()) {
            if(game.getDateStart().isAfter(today)){
                games.add(game);
            }
        }

        return games;
    }

}
