package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Liquidation;
import com.entrega1.trabajo.repository.LiquidationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LiquidationService {

    private final LiquidationRepository liquidationRepository;

    public LiquidationService(LiquidationRepository liquidationRepository) {
        this.liquidationRepository = liquidationRepository;
    }

    public List<Liquidation> findAll() {
        return liquidationRepository.findAll();
    }

    public Optional<Liquidation> findById(Integer id) {
        return liquidationRepository.findById(id);
    }

    public Liquidation save(Liquidation liquidation) {
        return liquidationRepository.save(liquidation);
    }

    public void deleteById(Integer id) {
        liquidationRepository.deleteById(id);
    }
}
