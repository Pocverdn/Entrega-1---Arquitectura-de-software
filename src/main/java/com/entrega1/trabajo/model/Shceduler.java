package java.com.entrega1.trabajo.model //Recuerden cambiar esto

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "Shceduler")
public class Shceduler {

   @OneToMany(mappedBy = "Shceduler", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<torneo> torneos= new ArrayList<>();


   @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<juego> juegos= new ArrayList<>();

}

