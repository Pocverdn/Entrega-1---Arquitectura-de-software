package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.RefereeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RefereeRequestRepository extends JpaRepository<RefereeRequest, Integer> {
    List<RefereeRequest> findByRefereeId(Integer refereeId);
}
