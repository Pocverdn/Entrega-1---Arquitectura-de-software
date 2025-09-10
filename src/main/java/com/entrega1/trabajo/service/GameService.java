package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Game;
import com.entrega1.trabajo.model.Liquidation;
import com.entrega1.trabajo.model.RefereeRequest;
import com.entrega1.trabajo.repository.GameRepository;
import com.entrega1.trabajo.repository.LiquidationRepository;
import com.entrega1.trabajo.repository.RefereeRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class GameService {
    private final GameRepository juegoRepository;
    private final RefereeRequestRepository refereeRequestRepository;
    private final LiquidationRepository liquidationRepository;

    public GameService(GameRepository juegoRepository,
                       RefereeRequestRepository refereeRequestRepository,
                       LiquidationRepository liquidationRepository) {
        this.juegoRepository = juegoRepository;
        this.refereeRequestRepository = refereeRequestRepository;
        this.liquidationRepository = liquidationRepository;
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
        // 1) Elimina todas las RefereeRequest que referencian este Game
        List<RefereeRequest> requests = refereeRequestRepository.findAll();
        for (RefereeRequest req : requests) {
            if (req.getGame() != null && Objects.equals(req.getGame().getId(), id)) {
                refereeRequestRepository.delete(req);
            }
        }

        // 2) Quitar referencias en liquidaciones (tabla de unión liquidation_games)
        Optional<Game> gameOpt = juegoRepository.findById(id);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            List<Liquidation> liquidations = liquidationRepository.findAll();
            for (Liquidation liq : liquidations) {
                if (liq.getGames() != null && liq.getGames().removeIf(g -> Objects.equals(g.getId(), id))) {
                    // Recalcular monto tras remover el game
                    liq.setTotalAmount(liq.calculateAmount());
                    liquidationRepository.save(liq);
                }
            }
        }

        // 3) Finalmente borrar el game
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
