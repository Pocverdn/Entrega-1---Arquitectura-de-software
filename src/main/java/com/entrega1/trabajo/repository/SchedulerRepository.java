package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Integer>{   
}
