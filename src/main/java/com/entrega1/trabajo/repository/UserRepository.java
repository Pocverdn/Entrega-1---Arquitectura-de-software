package com.entrega1.trabajo.repository;

import com.entrega1.trabajo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByUsername(String username);
}
