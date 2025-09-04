package com.entrega1.trabajo.service;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.repository.RefereeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class RefereeService {

    private final RefereeRepository refereeRepository;

    public RefereeService(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }
    
    public Referee createReferee(Referee referee){
        return refereeRepository.save(referee);
    }

    public List<Referee> getAllReferees() {
        return refereeRepository.findAll();
    }

    public Optional<Referee> getRefereeById(int id) {
        return refereeRepository.findById(id);
    }

    public void deleteRefereeById(int id) {
        refereeRepository.deleteById(id);
    }

    
}
