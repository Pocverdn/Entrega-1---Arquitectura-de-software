package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Juego;
import com.entrega1.trabajo.model.Tournament;
import com.entrega1.trabajo.repository.JuegoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JuegoService {
    private final JuegoRepository juegoRepository;

    public JuegoService(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    public List<Juego> findAll() {
        return juegoRepository.findAll();
    }

    public Optional<Juego> findById(Integer id) {
        return juegoRepository.findById(id);
    }

    public Juego save(Juego juego) {
        return juegoRepository.save(juego);
    }

    public void deleteById(Integer id) {
        juegoRepository.deleteById(id);
    }    

}
