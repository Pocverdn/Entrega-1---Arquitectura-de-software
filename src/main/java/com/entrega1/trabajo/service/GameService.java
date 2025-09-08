package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Game;
import com.entrega1.trabajo.repository.GameRepository;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class GameService {
    private final GameRepository juegoRepository;

    public GameService(GameRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    public List<Game> findAll() {
        return juegoRepository.findAll();
    }


    public List<Game> dateSort() {
        List<Game> sortedGames = juegoRepository.findAll();

        Collections.sort(sortedGames, new java.util.Comparator<Game>(){
        @Override
            public int compare(Game o1, Game o2) {
                LocalDate t1 = (o1.getDateStart());
                LocalDate t2 = (o2.getDateStart());
                return t2.getDayOfYear()-t1.getDayOfYear();
            }
        });

        return sortedGames;
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
