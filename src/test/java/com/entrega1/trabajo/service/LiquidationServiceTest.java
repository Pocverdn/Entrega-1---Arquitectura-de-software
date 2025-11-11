package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Liquidation;
import com.entrega1.trabajo.repository.LiquidationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  
class LiquidationServiceTest {

    @Mock
    private LiquidationRepository liquidationRepository;

    @InjectMocks
    private LiquidationService liquidationService;

    
    @Test
    void save_deberiaLlamarAlRepositorioYRetornarLaMismaEntidad() {
        
        Liquidation liquidation = new Liquidation();
        
        when(liquidationRepository.save(liquidation)).thenReturn(liquidation);

        
        Liquidation resultado = liquidationService.save(liquidation);

       
        assertNotNull(resultado);
        assertEquals(liquidation, resultado);
        
        verify(liquidationRepository, times(1)).save(liquidation);
    }

    
    @Test
    void findById_cuandoExiste_deberiaRetornarOptionalConLaLiquidacion() {
       
        Integer id = 1;
        Liquidation liquidation = new Liquidation();
        
        when(liquidationRepository.findById(id)).thenReturn(Optional.of(liquidation));

     
        Optional<Liquidation> resultado = liquidationService.findById(id);

       
        assertTrue(resultado.isPresent());
        assertEquals(liquidation, resultado.get());
        verify(liquidationRepository, times(1)).findById(id);
    }
}
