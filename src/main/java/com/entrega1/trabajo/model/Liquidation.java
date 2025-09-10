package com.entrega1.trabajo.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "liquidation")
public class Liquidation {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "liquidation_games",
        joinColumns = @JoinColumn(name = "liquidation_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private java.util.List<Game> games = new java.util.ArrayList<>();
    public java.util.List<Game> getGames() {
        return games;
    }

    public void setGames(java.util.List<Game> games) {
        this.games = games;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referee_id", nullable = false)
    private Referee referee;

    
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    public Liquidation() {}

    public Liquidation(Referee referee) {
        this.referee = referee;
    }

    public double calculateAmount() {
        if (games == null) return 0.0;
        return games.size() * 100000.0;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }

    // === Getters y Setters ===
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }



    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Liquidation{" +
                "id=" + id +
                ", refereeId=" + (referee != null ? referee.getId() : null) +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                '}';
    }
}
